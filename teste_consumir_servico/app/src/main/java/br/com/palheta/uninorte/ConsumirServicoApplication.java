package br.com.palheta.uninorte;

import android.app.Application;
import android.util.Log;

public class ConsumirServicoApplication extends Application {
    private static final String TAG = "ConsumirServicoApplication";
    private static ConsumirServicoApplication instance = null;

    public static ConsumirServicoApplication getInstance() {
        return instance; // Singleton
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "ConsumirServicoApplication.onCreate()");
        // Salva a instância para termos acesso como Singleton
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "ConsumirServicoApplication.onTerminate()");
    }
}
