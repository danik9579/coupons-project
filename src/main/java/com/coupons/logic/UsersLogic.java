package com.coupons.logic;

import com.coupons.dto.CompanyDto;
import com.coupons.dto.SuccessfulLoginData;
import com.coupons.dto.UserLoginData;
import com.coupons.entities.User;
import com.coupons.constants.Constants;
import com.coupons.dal.IUsersDal;
import com.coupons.dto.UserDto;
import com.coupons.enums.ErrorType;
import com.coupons.enums.UserType;
import com.coupons.exceptions.ServerException;
import com.coupons.utils.JWTUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsersLogic {
    private IUsersDal usersDal;

    @Autowired
    public UsersLogic(IUsersDal usersDal) {
        this.usersDal = usersDal;
    }

    public String login(UserLoginData userLoginData) throws ServerException, JsonProcessingException {
        SuccessfulLoginData successfulLoginData = usersDal.login(userLoginData.getUserName() , userLoginData.getPassword());
        if (successfulLoginData == null){
            throw new ServerException("Failed to login" , ErrorType.FAILED_LOGIN);
        }
        String token = JWTUtils.createJWT(successfulLoginData);
        return token;
    }

    public void addUser(User user) throws ServerException {
        validateUser(user);
        validateUserNameAvailability(user.getUserName());
        try {
            usersDal.save(user);
        }catch (Exception e){
            throw new ServerException("Error in creating User " + user.getUserName(), ErrorType.GENERAL_ERROR);
        }
    }

    public void updateUser(User user) throws ServerException {
        validateUser(user);
        validateUserNameAvailability(user.getUserName() , user.getId());
        try {
            usersDal.save(user);
        }catch (Exception e){
            throw new ServerException("Error in updating User" + user.getUserName(), ErrorType.GENERAL_ERROR);
        }
    }
    
    public void removeUser(long userId) throws ServerException {
        try {
            usersDal.deleteById(userId);
        }catch (Exception e){
            throw new ServerException("Error in removing User" + userId, ErrorType.GENERAL_ERROR);
        }
    }


    public UserDto getUser(long userId) throws ServerException {
        try {
            UserDto userDto = usersDal.findById(userId);
            return userDto;
        }catch (Exception e){
            throw new ServerException("Error in getting User" + userId, ErrorType.GENERAL_ERROR);
        }
    }

    public List<UserDto> getAllUsers(int page) throws ServerException {
        Pageable paging = PageRequest.of(page - 1 , Constants.RECORDS_PER_PAGE);
        try {
            List<UserDto> usersDto = usersDal.findAll(paging);
            return usersDto;
        }catch (Exception e){
            throw new ServerException("Error in getting all Users", ErrorType.GENERAL_ERROR);
        }
    }

    void validateUserNameAvailability(String userName) throws ServerException {
        if(usersDal.existsByUserName(userName)){
            throw new ServerException("The username is exist " + userName, ErrorType.INVALID_USERNAME_ALREADY_EXIST);
        }
    }

    void validateUserNameAvailability(String userName , long id) throws ServerException {
        if(usersDal.existsByUserNameInDifferentId(userName , id)){
            throw new ServerException("The username is already exist" + userName, ErrorType.INVALID_USERNAME_ALREADY_EXIST);
        }
    }

    void validateUser(User user) throws ServerException {
        validateUserName(user.getUserName());
        validatePassword(user.getPassword());
    }

    private void validateUserName(String userName) throws ServerException {
        if (userName.isBlank()) {
            throw new ServerException("The username is null ", ErrorType.USERNAME_IS_NULL);
        }

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);
        if (!matcher.matches()) {
            throw new ServerException("The username should be in mail format: " + userName, ErrorType.INVALID_USERNAME);
        }
    }

    private void validatePassword(String password) throws ServerException {
        if (password.isBlank()) {
            throw new ServerException("The password is null ", ErrorType.PASSWORD_IS_NULL);
        }

        if(password.length() < 6){
            throw new ServerException("The password is too short" + password , ErrorType.INVALID_PASSWORD_TOO_SHORT);
        }

        if(password.length() > 12){
            throw new ServerException("The password is too long" + password , ErrorType.INVALID_PASSWORD_TOO_LONG);
        }

        boolean isContainCapitalLetter = false;
        boolean isContainLowerCaseLetter = false;
        boolean isContainNumber = false;
        boolean isPasswordValid = false;
        int index = 0;
        int charAtIndex;

        while(!isPasswordValid && index < password.length()){
            charAtIndex = password.charAt(index);
            if(charAtIndex >= 97 && charAtIndex <= 122 && !isContainLowerCaseLetter){
                isContainLowerCaseLetter = true;
            }
            if(charAtIndex >= 65 && charAtIndex <= 90 && !isContainCapitalLetter){
                isContainCapitalLetter = true;
            }
            if(charAtIndex >= 48 && charAtIndex <= 57 && !isContainNumber){
                isContainNumber = true;
            }
            if((isContainCapitalLetter && isContainLowerCaseLetter && isContainNumber)){
                isPasswordValid = true;
            }
            index++;
        }

        if(!isPasswordValid){
            if (!isContainCapitalLetter){
                throw new ServerException("The password must contain at least one capital letter" + password , ErrorType.INVALID_PASSWORD_MISSING_CAPITAL_LETTER);
            }
            if (!isContainLowerCaseLetter){
                throw new ServerException("The password must contain at least one lower case letter" + password , ErrorType.INVALID_PASSWORD_MISSING_LOWER_CASE_LETTER);
            }
            if (!isContainNumber){
                throw new ServerException("The password must contain at least one number" + password , ErrorType.INVALID_PASSWORD_MISSING_NUMBER);
            }
        }
    }

}
