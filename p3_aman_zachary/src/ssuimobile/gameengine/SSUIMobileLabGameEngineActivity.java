package ssuimobile.gameengine;

import ssuimobile.gameengine.event.FSMEvent;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class SSUIMobileLabGameEngineActivity extends Activity {
    /** Called when the activity is first created. */
	GameEngineBase gb; //declared gb outside of onCreate to make it accessible in onStop()
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        gb = new GameEngineBase(R.raw.basic_fsmspec, this);
        
        //consumption testing
//        for(int i = 0; i <= 10; i++){
//        	FSMEvent noaction = new FSMEvent(i);
//        	Log.d("dispatch all", "dispatch all");
//            boolean consumed = gb.dispatchToAll(noaction);
//            Log.d("end dispatch all", "end dispatch all = "+consumed);
//            Log.d("dispatch try all", "dispatch try all");
//            consumed = gb.dispatchTryAll(noaction);
//            Log.d("end dispatch try all", "end dispatch try all = "+consumed);
//        }
        
        
        getActionBar().setTitle("Zaman Game Engine");   
//        getSupportActionBar().setTitle("Zaman Game Engine");
        
        
        setContentView(gb.finalInit());
    }
    
    @Override
    protected void onStop(){
    	super.onStop();
    	gb._tickHandler.stop();//stop the timer onStop()
    }
}