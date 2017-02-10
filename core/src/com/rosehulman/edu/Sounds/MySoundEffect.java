package com.rosehulman.edu.Sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by suj1 on 2/8/2017.
 */

public class MySoundEffect {
    private Sound mySound;
    private static boolean isMute;

    public MySoundEffect(String sound){
        this.mySound = Gdx.audio.newSound(Gdx.files.internal(sound));
    }

    public void playSound(){
        if(!isMute) {
            long id = this.mySound.play();
            this.mySound.setVolume(id, 0.05f);
            this.mySound.setLooping(id, false);
        }
    }

    public void dispose(){
        if(!isMute) {
            this.mySound.dispose();
        }
    }

    public static void setMute(boolean b){
        isMute = b;
    }

}
