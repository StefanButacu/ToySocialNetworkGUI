package com.toysocialnetworkgui.validator;

import com.toysocialnetworkgui.domain.Friendship;

public class FriendshipValidator implements Validator<Friendship> {

    private static FriendshipValidator instance;
    private FriendshipValidator(){

    }
    public static FriendshipValidator getInstance() {
        if(instance == null){
            instance = new FriendshipValidator();
        }
        return instance;
    }

    /**
     * Validates a friendship
     * @param friendship - the friendship to be validated
     * @throws ValidatorException - if the friendship contains the same email on both fields
     */
    @Override
    public void validate(Friendship friendship) throws ValidatorException {
        if (friendship.getFirst().equals(friendship.getSecond())) throw new ValidatorException("A user can't befriend himself");
    }
}
