package com.jonathanfullam.alexa.home;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jfullam on 5/19/16.
 */
public class HomeSkillSpeechletHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds;

    static {
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.echo-sdk-ams.app.02eb1bbb-3c92-4c68-8870-3157ecc5972f");
    }

    public HomeSkillSpeechletHandler() {
        super(new HomeSkillSpeechlet(), supportedApplicationIds);
    }
}
