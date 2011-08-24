package org.ruboto.callbacks;

import org.jruby.embed.ScriptingContainer;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.exceptions.RaiseException;
import org.ruboto.Script;

public class RubotoOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
  private ScriptingContainer __ruby__;

  public static final int CB_ITEM_SELECTED = 0;
  public static final int CB_NOTHING_SELECTED = 1;
  private IRubyObject[] callbackProcs = new IRubyObject[2];



  private ScriptingContainer getRuby() {
    if (__ruby__ == null) __ruby__ = Script.getRuby();
    return __ruby__;
  }

  public void setCallbackProc(int id, IRubyObject obj) {
    callbackProcs[id] = obj;
  }
	
  public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
    if (callbackProcs[CB_ITEM_SELECTED] != null) {
      try {
        getRuby().callMethod(callbackProcs[CB_ITEM_SELECTED], "call" , new Object[]{parent, view, position, id});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    }
  }

  public void onNothingSelected(android.widget.AdapterView<?> parent) {
    if (callbackProcs[CB_NOTHING_SELECTED] != null) {
      try {
        getRuby().callMethod(callbackProcs[CB_NOTHING_SELECTED], "call" , parent);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    }
  }
}
