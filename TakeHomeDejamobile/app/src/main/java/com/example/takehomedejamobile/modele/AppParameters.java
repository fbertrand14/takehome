package com.example.takehomedejamobile.modele;

/**
 * This singleton implementation is used to store all app parameter
 */
public class AppParameters {

    private static volatile AppParameters parametersInstance;

    private boolean aesEncyption = true;

    /**
     * Constructor
     */
    private AppParameters(){

    }

    /**
     * This function return the unique instance of App Parameter
     * @return
     */
    public static AppParameters getParameters(){
        if (parametersInstance == null){
            parametersInstance = new AppParameters();
        }
        return parametersInstance;
    }

    /**
     * This function Set if AES encyption is to be used
     * @param aesEncyption
     *      Boolean true if AES is to be used
     */
    public void setAesEncyption(boolean aesEncyption) {
        this.aesEncyption = aesEncyption;
    }

    /**
     * This fonction return the parameter for the use of AES encryption
     * @return
     *      Boolean true if AES is to be used
     */
    public boolean getAesEncyption(){
        return aesEncyption;
    }
}
