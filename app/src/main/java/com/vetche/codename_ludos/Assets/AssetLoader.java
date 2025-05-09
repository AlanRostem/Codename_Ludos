package com.vetche.codename_ludos.Assets;

import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.vetche.codename_ludos.Assets.Audio.Audio;
import com.vetche.codename_ludos.Assets.Audio.Music;
import com.vetche.codename_ludos.Assets.Graphics.SpriteMap;
import com.vetche.codename_ludos.Core.MainActivity;
import com.vetche.codename_ludos.LibraryTools.BitmapHelper;

import java.util.HashMap;
import java.util.Map;

public class AssetLoader {
    private HashMap<String, Asset> assets;

    private HashMap<String, Integer> queuedBitmapIDs;
    private HashMap<String, Integer> queuedAudioIDs;

    private BitmapFactory.Options options = new BitmapFactory.Options();
    private boolean allAssetsLoaded = false;

    private static AudioAttributes audioAttributes = new AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build();

    private SoundPool soundPool = /*new SoundPool(6, AudioManager.STREAM_MUSIC, 0); */new SoundPool.Builder()
            .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build();

    /*
    * To create an asset with a name string you must provide a prefix letter and
    * an underscore before the name in order to create the asset properly. This
    * allows the asset loader to distinguish which asset is of what type (sprite,
    * sound or song). Here are the following prefixes:
    *
    * SpriteMap: "s_YourName"
    * Audio:     "a_YourName"
    * Music:     "m_YourName"
     */

    public AssetLoader() {
        assets = new HashMap<>();
        queuedBitmapIDs = new HashMap<>();
        queuedAudioIDs = new HashMap<>();
        options.inScaled = false;
    }

    public void queueBitmapToLoad(String name, int resourceID) {
        queuedBitmapIDs.put(name, resourceID);
    }

    public void queueSoundToLoad(String name, int resourceID) {
        queuedAudioIDs.put(name, resourceID);
    }

    public Asset getAsset(String name) {
        return assets.get(name);
    }

    public void createAllQueuedAssets() {
        for (Map.Entry<String, Integer> entry : queuedBitmapIDs.entrySet()) {
            String bitmapName = entry.getKey();
            int resourceID = entry.getValue();
            if (bitmapName.charAt(0) == 's') {
                if (assets.containsKey(bitmapName)) {
                    assets.get(bitmapName).asSpriteMap().setBitmap(BitmapHelper.decodeResource(
                            MainActivity.gamePanel.getResources(), resourceID, options));
                    continue;
                }
                SpriteMap sm = new SpriteMap(
                        BitmapHelper.decodeResource(
                                MainActivity.gamePanel.getResources(), resourceID, options));
                assets.put(bitmapName, sm);
            }
        }
        queuedBitmapIDs.clear();

        for (Map.Entry<String, Integer> entry : queuedAudioIDs.entrySet()) {
            String audioName = entry.getKey();
            int resourceID = entry.getValue();
            switch (audioName.charAt(0)) {
                case 'a':
                    if (assets.containsKey(audioName)) {
                        assets.get(audioName).asAudio().reload();
                        continue;
                    }
                    assets.put(audioName, new Audio(soundPool, resourceID));
                    break;
                case 'm':
                    assets.put(audioName, new Music(resourceID));
                    break;
            }
        }
        queuedAudioIDs.clear();

        allAssetsLoaded = true;
    }

    public SoundPool getSoundPool() {
        return soundPool;
    }

    public void recycleAll() {
        for (Map.Entry<String, Asset> entry : assets.entrySet()) {
            entry.getValue().recycle();
        }
        soundPool.release();
    }

}
