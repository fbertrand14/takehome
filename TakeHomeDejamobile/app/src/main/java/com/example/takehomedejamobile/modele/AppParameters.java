package com.example.takehomedejamobile.modele;

public class AppParameters {

    private static volatile AppParameters parametersInstance;

    private boolean aesEncyption = true;

    private AppParameters(){

    }

    public static AppParameters getParameters(){
        if (parametersInstance == null){
            parametersInstance = new AppParameters();
        }
        return parametersInstance;
    }

    public void setAesEncyption(boolean aesEncyption) {
        this.aesEncyption = aesEncyption;
    }


    public boolean getAesEncyption(){
        return aesEncyption;
    }
}
