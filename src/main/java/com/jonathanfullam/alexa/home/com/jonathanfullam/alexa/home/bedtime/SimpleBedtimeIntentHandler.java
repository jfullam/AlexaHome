package com.jonathanfullam.alexa.home.com.jonathanfullam.alexa.home.bedtime;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Minutes;
import org.springframework.stereotype.Component;

/**
 * Created by jfullam on 5/26/16.
 */
@Component
public class SimpleBedtimeIntentHandler implements BedtimeIntentHandler {

    @Override
    public SpeechletResponse onIntent() {

        DateTime now = DateTime.now(DateTimeZone.forID("America/New_York"));
        DateTime bedtime = now.withTime(19, 30, 0, 0);



        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();

        if (now.isAfter(bedtime)) {
            speech.setText("It's past your bedtime!  Make sure you cleaned up any messes you made and then go to bed!");
            return SpeechletResponse.newTellResponse(speech);
        } else {
            int hours = Minutes.minutesBetween(now, bedtime).getValue(0) / 60;
            int minutes = Minutes.minutesBetween(now, bedtime).getValue(0) % 60;
            String bedtimeStr = "Your bedtime is in ";
            if (hours > 0) {
                if (hours > 1) {
                    bedtimeStr +=  hours + " hours and ";
                }
                else {
                    bedtimeStr +=  hours + " hour and ";
                }
            }

            bedtimeStr+= minutes + " minutes.";

            if (hours > 8) {
                bedtimeStr = bedtimeStr + " Did you seriously ask me that?  It's still the morning!";
            }
            else if (hours > 2) {
                bedtimeStr = bedtimeStr + " You still have plenty of time.  Go and play.";
            } else {
                bedtimeStr = bedtimeStr + " It's not too far away so maybe you should start winding down.  Don't forget to clean up your toys before bed too.";
            }

            speech.setText(bedtimeStr);
            return SpeechletResponse.newTellResponse(speech);
        }



    }


}
