package com.rosehulman.edu.Sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by suj1 on 2/8/2017.
 */

public class MySoundEffect {
    private Sound mySound;
    private static boolean isMute = false;
    private float volume;

    public MySoundEffect(String sound){
        this.mySound = Gdx.audio.newSound(Gdx.files.internal(sound));
        this.volume = 0.04f;
    }

    public void playSound(){
        if(!isMute) {
            System.out.println("play sound");
            long id = this.mySound.play();
            this.mySound.setVolume(id, volume);
            this.mySound.setLooping(id, false);
        }
    }

    public void dispose(){
        this.mySound.dispose();
    }

    public static void setMute(boolean b){
        isMute = b;
    }

    public void setVolume(float f) {
        this.volume = f;
    }
}
