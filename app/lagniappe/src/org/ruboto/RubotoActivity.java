package org.ruboto;

import java.io.IOException;

import org.jruby.Ruby;
import org.jruby.embed.ScriptingContainer;
import org.jruby.exceptions.RaiseException;
import org.jruby.javasupport.JavaUtil;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.ruboto.Script;

import android.app.ProgressDialog;
import android.os.Handler;

import com.app47.embeddedagent.EmbeddedAgentActivity;

public class RubotoActivity extends EmbeddedAgentActivity {
  private ScriptingContainer __ruby__;
  private String scriptName;
  private String rubyClassName;
  private Object rubyInstance;
  private int splash = 0;
  private String remoteVariable = "";
  public Object[] args;
  private ProgressDialog loadingDialog; 

  public static final int CB_ACTIVITY_RESULT = 0;
  public static final int CB_CHILD_TITLE_CHANGED = 1;
  public static final int CB_CONFIGURATION_CHANGED = 2;
  public static final int CB_CONTENT_CHANGED = 3;
  public static final int CB_CONTEXT_ITEM_SELECTED = 4;
  public static final int CB_CONTEXT_MENU_CLOSED = 5;
  public static final int CB_CREATE_CONTEXT_MENU = 6;
  public static final int CB_CREATE_DESCRIPTION = 7;
  public static final int CB_CREATE_OPTIONS_MENU = 8;
  public static final int CB_CREATE_PANEL_MENU = 9;
  public static final int CB_CREATE_PANEL_VIEW = 10;
  public static final int CB_CREATE_THUMBNAIL = 11;
  public static final int CB_CREATE_VIEW = 12;
  public static final int CB_DESTROY = 13;
  public static final int CB_KEY_DOWN = 14;
  public static final int CB_KEY_MULTIPLE = 15;
  public static final int CB_KEY_UP = 16;
  public static final int CB_LOW_MEMORY = 17;
  public static final int CB_MENU_ITEM_SELECTED = 18;
  public static final int CB_MENU_OPENED = 19;
  public static final int CB_NEW_INTENT = 20;
  public static final int CB_OPTIONS_ITEM_SELECTED = 21;
  public static final int CB_OPTIONS_MENU_CLOSED = 22;
  public static final int CB_PANEL_CLOSED = 23;
  public static final int CB_PAUSE = 24;
  public static final int CB_POST_CREATE = 25;
  public static final int CB_POST_RESUME = 26;
  public static final int CB_PREPARE_OPTIONS_MENU = 27;
  public static final int CB_PREPARE_PANEL = 28;
  public static final int CB_RESTART = 29;
  public static final int CB_RESTORE_INSTANCE_STATE = 30;
  public static final int CB_RESUME = 31;
  public static final int CB_RETAIN_NON_CONFIGURATION_INSTANCE = 32;
  public static final int CB_SAVE_INSTANCE_STATE = 33;
  public static final int CB_SEARCH_REQUESTED = 34;
  public static final int CB_START = 35;
  public static final int CB_STOP = 36;
  public static final int CB_TITLE_CHANGED = 37;
  public static final int CB_TOUCH_EVENT = 38;
  public static final int CB_TRACKBALL_EVENT = 39;
  public static final int CB_WINDOW_ATTRIBUTES_CHANGED = 40;
  public static final int CB_WINDOW_FOCUS_CHANGED = 41;
  public static final int CB_USER_INTERACTION = 42;
  public static final int CB_USER_LEAVE_HINT = 43;
  public static final int CB_ATTACHED_TO_WINDOW = 44;
  public static final int CB_BACK_PRESSED = 45;
  public static final int CB_DETACHED_FROM_WINDOW = 46;
  public static final int CB_KEY_LONG_PRESS = 47;
  public static final int CB_CREATE_DIALOG = 48;
  public static final int CB_PREPARE_DIALOG = 49;
  private IRubyObject[] callbackProcs = new IRubyObject[50];

  private ScriptingContainer getRuby() {
    if (__ruby__ == null) {
        __ruby__ = Script.getRuby();

        if (__ruby__ == null) {
          Script.setUpJRuby(this);
          __ruby__ = Script.getRuby();
        }
    }

    return __ruby__;
  }

  public void setCallbackProc(int id, IRubyObject obj) {
    callbackProcs[id] = obj;
  }
	
  public RubotoActivity setRemoteVariable(String var) {
    remoteVariable = ((var == null) ? "" : (var + "."));
    return this;
  }

  public void setSplash(int a_res){
    splash = a_res;
  }

  public void setScriptName(String name){
    scriptName = name;
  }

  /****************************************************************************************
   * 
   *  Activity Lifecycle: onCreate
   */
	
  @Override
  public void onCreate(android.os.Bundle arg0) {
    args = new Object[1];
    args[0] = arg0;

    android.os.Bundle configBundle = getIntent().getBundleExtra("RubotoActivity Config");

    if (configBundle != null && configBundle.containsKey("Theme")) {
      setTheme(configBundle.getInt("Theme"));
    }

    super.onCreate(arg0);
    
    if (Script.getRuby() != null) {
      backgroundCreate();
    	finishCreate();
    } else {
      if (splash == 0) {
        loadingDialog = ProgressDialog.show(this, null, "Loading...", true, false);
      } else {
        requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        setContentView(splash);
      }
      loadingThread.start();
    }
  }

  private final Handler loadingHandler = new Handler();
  
  private final Thread loadingThread = new Thread() {
      public void run(){
        backgroundCreate();
        loadingHandler.post(loadingComplete);
      }
  };
  
  private final Runnable loadingComplete = new Runnable(){
    public void run(){
      if (loadingDialog != null) loadingDialog.dismiss();
      finishCreate();
      onStart();
      onResume();
    }
  };

  private void backgroundCreate() {
      getRuby().put("$activity", this);
      getRuby().put("$bundle", args[0]);
  }

  private void finishCreate() {
    android.os.Bundle configBundle = getIntent().getBundleExtra("RubotoActivity Config");

    if (configBundle != null) {
      setRemoteVariable(configBundle.getString("Remote Variable"));
      if (configBundle.getBoolean("Define Remote Variable")) {
        Script.defineGlobalVariable(configBundle.getString("Remote Variable"), this);
        setRemoteVariable(configBundle.getString("Remote Variable"));
      }
      if (configBundle.getString("Initialize Script") != null) {
        Script.execute(configBundle.getString("Initialize Script"));
      }
      Script.execute(remoteVariable + "on_create($bundle)");
    } else {
      try {
          new Script(scriptName).execute();
          /* TODO(uwe): Add a way to add callbacks from a class or just forward all calls to the instance
          rubyClassName = this.getClass().getSimpleName();
          if (getRuby().get(rubyClassName) != null) {
  		    rubyInstance = Script.exec(rubyClassName + ".new");
  		    getRuby().callMethod(rubyInstance, "on_create", configBundle);
          }
          */
      } catch(IOException e){
        e.printStackTrace();
        ProgressDialog.show(this, "Script failed", "Something bad happened", true, true);
      }
    }
  }

  /****************************************************************************************
   * 
   *  Generated Methods
   */

  public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
    if (callbackProcs[CB_ACTIVITY_RESULT] != null) {
      super.onActivityResult(requestCode, resultCode, data);
      try {
        getRuby().callMethod(callbackProcs[CB_ACTIVITY_RESULT], "call" , new Object[]{requestCode, resultCode, data});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  public void onChildTitleChanged(android.app.Activity childActivity, java.lang.CharSequence title) {
    if (callbackProcs[CB_CHILD_TITLE_CHANGED] != null) {
      super.onChildTitleChanged(childActivity, title);
      try {
        getRuby().callMethod(callbackProcs[CB_CHILD_TITLE_CHANGED], "call" , new Object[]{childActivity, title});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onChildTitleChanged(childActivity, title);
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

  public void onContentChanged() {
    if (callbackProcs[CB_CONTENT_CHANGED] != null) {
      super.onContentChanged();
      try {
        getRuby().callMethod(callbackProcs[CB_CONTENT_CHANGED], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onContentChanged();
    }
  }

  public boolean onContextItemSelected(android.view.MenuItem item) {
    if (callbackProcs[CB_CONTEXT_ITEM_SELECTED] != null) {
      super.onContextItemSelected(item);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_CONTEXT_ITEM_SELECTED], "call" , item, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onContextItemSelected(item);
    }
  }

  public void onContextMenuClosed(android.view.Menu menu) {
    if (callbackProcs[CB_CONTEXT_MENU_CLOSED] != null) {
      super.onContextMenuClosed(menu);
      try {
        getRuby().callMethod(callbackProcs[CB_CONTEXT_MENU_CLOSED], "call" , menu);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onContextMenuClosed(menu);
    }
  }

  public void onCreateContextMenu(android.view.ContextMenu menu, android.view.View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
    if (callbackProcs[CB_CREATE_CONTEXT_MENU] != null) {
      super.onCreateContextMenu(menu, v, menuInfo);
      try {
        getRuby().callMethod(callbackProcs[CB_CREATE_CONTEXT_MENU], "call" , new Object[]{menu, v, menuInfo});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onCreateContextMenu(menu, v, menuInfo);
    }
  }

  public java.lang.CharSequence onCreateDescription() {
    if (callbackProcs[CB_CREATE_DESCRIPTION] != null) {
      super.onCreateDescription();
      try {
        return (java.lang.CharSequence) getRuby().callMethod(callbackProcs[CB_CREATE_DESCRIPTION], "call" , java.lang.CharSequence.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return null;
      }
    } else {
      return super.onCreateDescription();
    }
  }

  public boolean onCreateOptionsMenu(android.view.Menu menu) {
    if (callbackProcs[CB_CREATE_OPTIONS_MENU] != null) {
      super.onCreateOptionsMenu(menu);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_CREATE_OPTIONS_MENU], "call" , menu, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onCreateOptionsMenu(menu);
    }
  }

  public boolean onCreatePanelMenu(int featureId, android.view.Menu menu) {
    if (callbackProcs[CB_CREATE_PANEL_MENU] != null) {
      super.onCreatePanelMenu(featureId, menu);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_CREATE_PANEL_MENU], "call" , new Object[]{featureId, menu}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onCreatePanelMenu(featureId, menu);
    }
  }

  public android.view.View onCreatePanelView(int featureId) {
    if (callbackProcs[CB_CREATE_PANEL_VIEW] != null) {
      super.onCreatePanelView(featureId);
      try {
        return (android.view.View) getRuby().callMethod(callbackProcs[CB_CREATE_PANEL_VIEW], "call" , featureId, android.view.View.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return null;
      }
    } else {
      return super.onCreatePanelView(featureId);
    }
  }

  public boolean onCreateThumbnail(android.graphics.Bitmap outBitmap, android.graphics.Canvas canvas) {
    if (callbackProcs[CB_CREATE_THUMBNAIL] != null) {
      super.onCreateThumbnail(outBitmap, canvas);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_CREATE_THUMBNAIL], "call" , new Object[]{outBitmap, canvas}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onCreateThumbnail(outBitmap, canvas);
    }
  }

  public android.view.View onCreateView(java.lang.String name, android.content.Context context, android.util.AttributeSet attrs) {
    if (callbackProcs[CB_CREATE_VIEW] != null) {
      super.onCreateView(name, context, attrs);
      try {
        return (android.view.View) getRuby().callMethod(callbackProcs[CB_CREATE_VIEW], "call" , new Object[]{name, context, attrs}, android.view.View.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return null;
      }
    } else {
      return super.onCreateView(name, context, attrs);
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

  public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
    if (callbackProcs[CB_KEY_DOWN] != null) {
      super.onKeyDown(keyCode, event);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_KEY_DOWN], "call" , new Object[]{keyCode, event}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onKeyDown(keyCode, event);
    }
  }

  public boolean onKeyMultiple(int keyCode, int repeatCount, android.view.KeyEvent event) {
    if (callbackProcs[CB_KEY_MULTIPLE] != null) {
      super.onKeyMultiple(keyCode, repeatCount, event);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_KEY_MULTIPLE], "call" , new Object[]{keyCode, repeatCount, event}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onKeyMultiple(keyCode, repeatCount, event);
    }
  }

  public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
    if (callbackProcs[CB_KEY_UP] != null) {
      super.onKeyUp(keyCode, event);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_KEY_UP], "call" , new Object[]{keyCode, event}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onKeyUp(keyCode, event);
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

  public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
    if (callbackProcs[CB_MENU_ITEM_SELECTED] != null) {
      super.onMenuItemSelected(featureId, item);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_MENU_ITEM_SELECTED], "call" , new Object[]{featureId, item}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onMenuItemSelected(featureId, item);
    }
  }

  public boolean onMenuOpened(int featureId, android.view.Menu menu) {
    if (callbackProcs[CB_MENU_OPENED] != null) {
      super.onMenuOpened(featureId, menu);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_MENU_OPENED], "call" , new Object[]{featureId, menu}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onMenuOpened(featureId, menu);
    }
  }

  public void onNewIntent(android.content.Intent intent) {
    if (callbackProcs[CB_NEW_INTENT] != null) {
      super.onNewIntent(intent);
      try {
        getRuby().callMethod(callbackProcs[CB_NEW_INTENT], "call" , intent);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onNewIntent(intent);
    }
  }

  public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (callbackProcs[CB_OPTIONS_ITEM_SELECTED] != null) {
      super.onOptionsItemSelected(item);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_OPTIONS_ITEM_SELECTED], "call" , item, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onOptionsItemSelected(item);
    }
  }

  public void onOptionsMenuClosed(android.view.Menu menu) {
    if (callbackProcs[CB_OPTIONS_MENU_CLOSED] != null) {
      super.onOptionsMenuClosed(menu);
      try {
        getRuby().callMethod(callbackProcs[CB_OPTIONS_MENU_CLOSED], "call" , menu);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onOptionsMenuClosed(menu);
    }
  }

  public void onPanelClosed(int featureId, android.view.Menu menu) {
    if (callbackProcs[CB_PANEL_CLOSED] != null) {
      super.onPanelClosed(featureId, menu);
      try {
        getRuby().callMethod(callbackProcs[CB_PANEL_CLOSED], "call" , new Object[]{featureId, menu});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onPanelClosed(featureId, menu);
    }
  }

  public void onPause() {
    if (callbackProcs[CB_PAUSE] != null) {
      super.onPause();
      try {
        getRuby().callMethod(callbackProcs[CB_PAUSE], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onPause();
    }
  }

  public void onPostCreate(android.os.Bundle savedInstanceState) {
    if (callbackProcs[CB_POST_CREATE] != null) {
      super.onPostCreate(savedInstanceState);
      try {
        getRuby().callMethod(callbackProcs[CB_POST_CREATE], "call" , savedInstanceState);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onPostCreate(savedInstanceState);
    }
  }

  public void onPostResume() {
    if (callbackProcs[CB_POST_RESUME] != null) {
      super.onPostResume();
      try {
        getRuby().callMethod(callbackProcs[CB_POST_RESUME], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onPostResume();
    }
  }

  public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    if (callbackProcs[CB_PREPARE_OPTIONS_MENU] != null) {
      super.onPrepareOptionsMenu(menu);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_PREPARE_OPTIONS_MENU], "call" , menu, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onPrepareOptionsMenu(menu);
    }
  }

  public boolean onPreparePanel(int featureId, android.view.View view, android.view.Menu menu) {
    if (callbackProcs[CB_PREPARE_PANEL] != null) {
      super.onPreparePanel(featureId, view, menu);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_PREPARE_PANEL], "call" , new Object[]{featureId, view, menu}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onPreparePanel(featureId, view, menu);
    }
  }

  public void onRestart() {
    if (callbackProcs[CB_RESTART] != null) {
      super.onRestart();
      try {
        getRuby().callMethod(callbackProcs[CB_RESTART], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onRestart();
    }
  }

  public void onRestoreInstanceState(android.os.Bundle savedInstanceState) {
    if (callbackProcs[CB_RESTORE_INSTANCE_STATE] != null) {
      super.onRestoreInstanceState(savedInstanceState);
      try {
        getRuby().callMethod(callbackProcs[CB_RESTORE_INSTANCE_STATE], "call" , savedInstanceState);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onRestoreInstanceState(savedInstanceState);
    }
  }

  public void onResume() {
    if (callbackProcs[CB_RESUME] != null) {
      super.onResume();
      try {
        getRuby().callMethod(callbackProcs[CB_RESUME], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onResume();
    }
  }

  public java.lang.Object onRetainNonConfigurationInstance() {
    if (callbackProcs[CB_RETAIN_NON_CONFIGURATION_INSTANCE] != null) {
      super.onRetainNonConfigurationInstance();
      try {
        return (java.lang.Object) getRuby().callMethod(callbackProcs[CB_RETAIN_NON_CONFIGURATION_INSTANCE], "call" , java.lang.Object.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return null;
      }
    } else {
      return super.onRetainNonConfigurationInstance();
    }
  }

  public void onSaveInstanceState(android.os.Bundle outState) {
    if (callbackProcs[CB_SAVE_INSTANCE_STATE] != null) {
      super.onSaveInstanceState(outState);
      try {
        getRuby().callMethod(callbackProcs[CB_SAVE_INSTANCE_STATE], "call" , outState);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onSaveInstanceState(outState);
    }
  }

  public boolean onSearchRequested() {
    if (callbackProcs[CB_SEARCH_REQUESTED] != null) {
      super.onSearchRequested();
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_SEARCH_REQUESTED], "call" , Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onSearchRequested();
    }
  }

  public void onStart() {
    if (callbackProcs[CB_START] != null) {
      super.onStart();
      try {
        getRuby().callMethod(callbackProcs[CB_START], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onStart();
    }
  }

  public void onStop() {
    if (callbackProcs[CB_STOP] != null) {
      super.onStop();
      try {
        getRuby().callMethod(callbackProcs[CB_STOP], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onStop();
    }
  }

  public void onTitleChanged(java.lang.CharSequence title, int color) {
    if (callbackProcs[CB_TITLE_CHANGED] != null) {
      super.onTitleChanged(title, color);
      try {
        getRuby().callMethod(callbackProcs[CB_TITLE_CHANGED], "call" , new Object[]{title, color});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onTitleChanged(title, color);
    }
  }

  public boolean onTouchEvent(android.view.MotionEvent event) {
    if (callbackProcs[CB_TOUCH_EVENT] != null) {
      super.onTouchEvent(event);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_TOUCH_EVENT], "call" , event, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onTouchEvent(event);
    }
  }

  public boolean onTrackballEvent(android.view.MotionEvent event) {
    if (callbackProcs[CB_TRACKBALL_EVENT] != null) {
      super.onTrackballEvent(event);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_TRACKBALL_EVENT], "call" , event, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onTrackballEvent(event);
    }
  }

  public void onWindowAttributesChanged(android.view.WindowManager.LayoutParams params) {
    if (callbackProcs[CB_WINDOW_ATTRIBUTES_CHANGED] != null) {
      super.onWindowAttributesChanged(params);
      try {
        getRuby().callMethod(callbackProcs[CB_WINDOW_ATTRIBUTES_CHANGED], "call" , params);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onWindowAttributesChanged(params);
    }
  }

  public void onWindowFocusChanged(boolean hasFocus) {
    if (callbackProcs[CB_WINDOW_FOCUS_CHANGED] != null) {
      super.onWindowFocusChanged(hasFocus);
      try {
        getRuby().callMethod(callbackProcs[CB_WINDOW_FOCUS_CHANGED], "call" , hasFocus);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onWindowFocusChanged(hasFocus);
    }
  }

  public void onUserInteraction() {
    if (callbackProcs[CB_USER_INTERACTION] != null) {
      super.onUserInteraction();
      try {
        getRuby().callMethod(callbackProcs[CB_USER_INTERACTION], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onUserInteraction();
    }
  }

  public void onUserLeaveHint() {
    if (callbackProcs[CB_USER_LEAVE_HINT] != null) {
      super.onUserLeaveHint();
      try {
        getRuby().callMethod(callbackProcs[CB_USER_LEAVE_HINT], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onUserLeaveHint();
    }
  }

  public void onAttachedToWindow() {
    if (callbackProcs[CB_ATTACHED_TO_WINDOW] != null) {
      super.onAttachedToWindow();
      try {
        getRuby().callMethod(callbackProcs[CB_ATTACHED_TO_WINDOW], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onAttachedToWindow();
    }
  }

  public void onBackPressed() {
    if (callbackProcs[CB_BACK_PRESSED] != null) {
      super.onBackPressed();
      try {
        getRuby().callMethod(callbackProcs[CB_BACK_PRESSED], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onBackPressed();
    }
  }

  public void onDetachedFromWindow() {
    if (callbackProcs[CB_DETACHED_FROM_WINDOW] != null) {
      super.onDetachedFromWindow();
      try {
        getRuby().callMethod(callbackProcs[CB_DETACHED_FROM_WINDOW], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onDetachedFromWindow();
    }
  }

  public boolean onKeyLongPress(int keyCode, android.view.KeyEvent event) {
    if (callbackProcs[CB_KEY_LONG_PRESS] != null) {
      super.onKeyLongPress(keyCode, event);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_KEY_LONG_PRESS], "call" , new Object[]{keyCode, event}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onKeyLongPress(keyCode, event);
    }
  }

  public android.app.Dialog onCreateDialog(int id, android.os.Bundle args) {
    if (callbackProcs[CB_CREATE_DIALOG] != null) {
      super.onCreateDialog(id, args);
      try {
        return (android.app.Dialog) getRuby().callMethod(callbackProcs[CB_CREATE_DIALOG], "call" , new Object[]{id, args}, android.app.Dialog.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return null;
      }
    } else {
      return super.onCreateDialog(id, args);
    }
  }

  public void onPrepareDialog(int id, android.app.Dialog dialog, android.os.Bundle args) {
    if (callbackProcs[CB_PREPARE_DIALOG] != null) {
      super.onPrepareDialog(id, dialog, args);
      try {
        getRuby().callMethod(callbackProcs[CB_PREPARE_DIALOG], "call" , new Object[]{id, dialog, args});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onPrepareDialog(id, dialog, args);
    }
  }
}	

