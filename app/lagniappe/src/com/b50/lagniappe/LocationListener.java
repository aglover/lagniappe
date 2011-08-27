package com.b50.lagniappe;

import org.jruby.embed.ScriptingContainer;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.exceptions.RaiseException;
import org.ruboto.Script;

public class LocationListener implements android.location.LocationListener {
  private ScriptingContainer __ruby__;

  public static final int CB_LOCATION_CHANGED = 0;
  public static final int CB_PROVIDER_DISABLED = 1;
  public static final int CB_PROVIDER_ENABLED = 2;
  public static final int CB_STATUS_CHANGED = 3;
  private IRubyObject[] callbackProcs = new IRubyObject[4];



  private ScriptingContainer getRuby() {
    if (__ruby__ == null) __ruby__ = Script.getRuby();
    return __ruby__;
  }

  public void setCallbackProc(int id, IRubyObject obj) {
    callbackProcs[id] = obj;
  }
	
  public void onLocationChanged(android.location.Location location) {
    if (callbackProcs[CB_LOCATION_CHANGED] != null) {
      try {
        getRuby().callMethod(callbackProcs[CB_LOCATION_CHANGED], "call" , location);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    }
  }

  public void onProviderDisabled(java.lang.String provider) {
    if (callbackProcs[CB_PROVIDER_DISABLED] != null) {
      try {
        getRuby().callMethod(callbackProcs[CB_PROVIDER_DISABLED], "call" , provider);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    }
  }

  public void onProviderEnabled(java.lang.String provider) {
    if (callbackProcs[CB_PROVIDER_ENABLED] != null) {
      try {
        getRuby().callMethod(callbackProcs[CB_PROVIDER_ENABLED], "call" , provider);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    }
  }

  public void onStatusChanged(java.lang.String provider, int status, android.os.Bundle extras) {
    if (callbackProcs[CB_STATUS_CHANGED] != null) {
      try {
        getRuby().callMethod(callbackProcs[CB_STATUS_CHANGED], "call" , new Object[]{provider, status, extras});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    }
  }
}
