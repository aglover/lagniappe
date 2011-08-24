package org.ruboto;

import org.jruby.Ruby;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.embed.ScriptingContainer;
import org.jruby.exceptions.RaiseException;
import org.ruboto.Script;
import java.io.IOException;
import android.app.ProgressDialog;

public abstract class RubotoService extends android.app.Service {
  private ScriptingContainer __ruby__;
  private String scriptName;
  private String remoteVariable = "";
  public Object[] args;

  public static final int CB_BIND = 0;
  public static final int CB_CONFIGURATION_CHANGED = 1;
  public static final int CB_DESTROY = 2;
  public static final int CB_LOW_MEMORY = 3;
  public static final int CB_REBIND = 4;
  public static final int CB_UNBIND = 5;
  public static final int CB_START_COMMAND = 6;
  private IRubyObject[] callbackProcs = new IRubyObject[7];

  private ScriptingContainer getRuby() {
    if (__ruby__ == null) __ruby__ = Script.getRuby();

    if (__ruby__ == null) {
      Script.setUpJRuby(this);
      __ruby__ = Script.getRuby();
    }

    return __ruby__;
  }

  public void setCallbackProc(int id, IRubyObject obj) {
    callbackProcs[id] = obj;
  }
	
  public RubotoService setRemoteVariable(String var) {
    remoteVariable = ((var == null) ? "" : (var + "."));
    return this;
  }

  public void setScriptName(String name){
    scriptName = name;
  }

  /****************************************************************************************
   * 
   *  Activity Lifecycle: onCreate
   */
	
  @Override
  public void onCreate() {
    args = new Object[0];

    super.onCreate();

    getRuby();

    Script.defineGlobalVariable("$service", this);

    try {
      new Script(scriptName).execute();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  /****************************************************************************************
   * 
   *  Generated Methods
   */

  public android.os.IBinder onBind(android.content.Intent intent) {
    if (callbackProcs[CB_BIND] != null) {
      try {
        return (android.os.IBinder) getRuby().callMethod(callbackProcs[CB_BIND], "call" , intent, android.os.IBinder.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return null;
      }
    } else {
      return null;
    }
  }

  public void onConfigurationChanged(android.content.res.Configuration newConfig) {
    if (callbackProcs[CB_CONFIGURATION_CHANGED] != null) {
      super.onConfigurationChanged(newConfig);
      try {
        getRuby().callMethod(callbackProcs[CB_CONFIGURATION_CHANGED], "call" , newConfig);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onConfigurationChanged(newConfig);
    }
  }

  public void onDestroy() {
    if (callbackProcs[CB_DESTROY] != null) {
      super.onDestroy();
      try {
        getRuby().callMethod(callbackProcs[CB_DESTROY], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onDestroy();
    }
  }

  public void onLowMemory() {
    if (callbackProcs[CB_LOW_MEMORY] != null) {
      super.onLowMemory();
      try {
        getRuby().callMethod(callbackProcs[CB_LOW_MEMORY], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onLowMemory();
    }
  }

  public void onRebind(android.content.Intent intent) {
    if (callbackProcs[CB_REBIND] != null) {
      super.onRebind(intent);
      try {
        getRuby().callMethod(callbackProcs[CB_REBIND], "call" , intent);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onRebind(intent);
    }
  }

  public boolean onUnbind(android.content.Intent intent) {
    if (callbackProcs[CB_UNBIND] != null) {
      super.onUnbind(intent);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_UNBIND], "call" , intent, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onUnbind(intent);
    }
  }

  public int onStartCommand(android.content.Intent intent, int flags, int startId) {
    if (callbackProcs[CB_START_COMMAND] != null) {
      super.onStartCommand(intent, flags, startId);
      try {
        return (Integer) getRuby().callMethod(callbackProcs[CB_START_COMMAND], "call" , new Object[]{intent, flags, startId}, Integer.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return 0;
      }
    } else {
      return super.onStartCommand(intent, flags, startId);
    }
  }
}	


