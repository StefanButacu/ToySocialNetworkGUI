package com.toysocialnetworkgui.validator;

import com.toysocialnetworkgui.domain.Event;

public class EventValidator implements  Validator<Event>{

    private static EventValidator instance;
    private EventValidator(){

    }
    public static EventValidator getInstance() {
        if(instance == null){
            instance = new EventValidator();
        }
        return instance;
    }
    @Override
    public void validate(Event event) throws ValidatorException {
        if (event.getName().isEmpty() ||
            event.getDescription().isEmpty() ||
            event.getLocation().isEmpty() ||
            event.getCategory().isEmpty() ||
            event.getStart() == null ||
            event.getEnd() == null) throw  new ValidatorException("Fill all fields!");
    }
}
