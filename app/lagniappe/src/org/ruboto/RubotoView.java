package org.ruboto;

import org.jruby.embed.ScriptingContainer;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.exceptions.RaiseException;
import org.ruboto.Script;

public class RubotoView extends android.view.View {
  private ScriptingContainer __ruby__;

  public static final int CB_ANIMATION_END = 0;
  public static final int CB_ANIMATION_START = 1;
  public static final int CB_ATTACHED_TO_WINDOW = 2;
  public static final int CB_CREATE_CONTEXT_MENU = 3;
  public static final int CB_CREATE_DRAWABLE_STATE = 4;
  public static final int CB_DETACHED_FROM_WINDOW = 5;
  public static final int CB_DRAW = 6;
  public static final int CB_FINISH_INFLATE = 7;
  public static final int CB_FOCUS_CHANGED = 8;
  public static final int CB_KEY_DOWN = 9;
  public static final int CB_KEY_MULTIPLE = 10;
  public static final int CB_KEY_SHORTCUT = 11;
  public static final int CB_KEY_UP = 12;
  public static final int CB_LAYOUT = 13;
  public static final int CB_MEASURE = 14;
  public static final int CB_RESTORE_INSTANCE_STATE = 15;
  public static final int CB_SAVE_INSTANCE_STATE = 16;
  public static final int CB_SCROLL_CHANGED = 17;
  public static final int CB_SET_ALPHA = 18;
  public static final int CB_SIZE_CHANGED = 19;
  public static final int CB_TOUCH_EVENT = 20;
  public static final int CB_TRACKBALL_EVENT = 21;
  public static final int CB_WINDOW_FOCUS_CHANGED = 22;
  public static final int CB_WINDOW_VISIBILITY_CHANGED = 23;
  public static final int CB_CHECK_IS_TEXT_EDITOR = 24;
  public static final int CB_CREATE_INPUT_CONNECTION = 25;
  public static final int CB_FINISH_TEMPORARY_DETACH = 26;
  public static final int CB_KEY_PRE_IME = 27;
  public static final int CB_START_TEMPORARY_DETACH = 28;
  public static final int CB_KEY_LONG_PRESS = 29;
  public static final int CB_CONFIGURATION_CHANGED = 30;
  public static final int CB_DISPLAY_HINT = 31;
  public static final int CB_VISIBILITY_CHANGED = 32;
  private IRubyObject[] callbackProcs = new IRubyObject[33];

  public  RubotoView(android.content.Context context) {
    super(context);
  }

  public  RubotoView(android.content.Context context, android.util.AttributeSet attrs) {
    super(context, attrs);
  }

  public  RubotoView(android.content.Context context, android.util.AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  private ScriptingContainer getRuby() {
    if (__ruby__ == null) __ruby__ = Script.getRuby();
    return __ruby__;
  }

  public void setCallbackProc(int id, IRubyObject obj) {
    callbackProcs[id] = obj;
  }
	
  public void onAnimationEnd() {
    if (callbackProcs[CB_ANIMATION_END] != null) {
      super.onAnimationEnd();
      try {
        getRuby().callMethod(callbackProcs[CB_ANIMATION_END], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onAnimationEnd();
    }
  }

  public void onAnimationStart() {
    if (callbackProcs[CB_ANIMATION_START] != null) {
      super.onAnimationStart();
      try {
        getRuby().callMethod(callbackProcs[CB_ANIMATION_START], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onAnimationStart();
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

  public void onCreateContextMenu(android.view.ContextMenu menu) {
    if (callbackProcs[CB_CREATE_CONTEXT_MENU] != null) {
      super.onCreateContextMenu(menu);
      try {
        getRuby().callMethod(callbackProcs[CB_CREATE_CONTEXT_MENU], "call" , menu);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onCreateContextMenu(menu);
    }
  }

  public int[] onCreateDrawableState(int extraSpace) {
    if (callbackProcs[CB_CREATE_DRAWABLE_STATE] != null) {
      super.onCreateDrawableState(extraSpace);
      try {
        return (int[]) getRuby().callMethod(callbackProcs[CB_CREATE_DRAWABLE_STATE], "call" , extraSpace, int[].class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return null;
      }
    } else {
      return super.onCreateDrawableState(extraSpace);
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

  public void onDraw(android.graphics.Canvas canvas) {
    if (callbackProcs[CB_DRAW] != null) {
      super.onDraw(canvas);
      try {
        getRuby().callMethod(callbackProcs[CB_DRAW], "call" , canvas);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onDraw(canvas);
    }
  }

  public void onFinishInflate() {
    if (callbackProcs[CB_FINISH_INFLATE] != null) {
      super.onFinishInflate();
      try {
        getRuby().callMethod(callbackProcs[CB_FINISH_INFLATE], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onFinishInflate();
    }
  }

  public void onFocusChanged(boolean gainFocus, int direction, android.graphics.Rect previouslyFocusedRect) {
    if (callbackProcs[CB_FOCUS_CHANGED] != null) {
      super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
      try {
        getRuby().callMethod(callbackProcs[CB_FOCUS_CHANGED], "call" , new Object[]{gainFocus, direction, previouslyFocusedRect});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
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

  public boolean onKeyShortcut(int keyCode, android.view.KeyEvent event) {
    if (callbackProcs[CB_KEY_SHORTCUT] != null) {
      super.onKeyShortcut(keyCode, event);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_KEY_SHORTCUT], "call" , new Object[]{keyCode, event}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onKeyShortcut(keyCode, event);
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

  public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    if (callbackProcs[CB_LAYOUT] != null) {
      super.onLayout(changed, left, top, right, bottom);
      try {
        getRuby().callMethod(callbackProcs[CB_LAYOUT], "call" , new Object[]{changed, left, top, right, bottom});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onLayout(changed, left, top, right, bottom);
    }
  }

  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (callbackProcs[CB_MEASURE] != null) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      try {
        getRuby().callMethod(callbackProcs[CB_MEASURE], "call" , new Object[]{widthMeasureSpec, heightMeasureSpec});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
  }

  public void onRestoreInstanceState(android.os.Parcelable state) {
    if (callbackProcs[CB_RESTORE_INSTANCE_STATE] != null) {
      super.onRestoreInstanceState(state);
      try {
        getRuby().callMethod(callbackProcs[CB_RESTORE_INSTANCE_STATE], "call" , state);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onRestoreInstanceState(state);
    }
  }

  public android.os.Parcelable onSaveInstanceState() {
    if (callbackProcs[CB_SAVE_INSTANCE_STATE] != null) {
      super.onSaveInstanceState();
      try {
        return (android.os.Parcelable) getRuby().callMethod(callbackProcs[CB_SAVE_INSTANCE_STATE], "call" , android.os.Parcelable.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return null;
      }
    } else {
      return super.onSaveInstanceState();
    }
  }

  public void onScrollChanged(int l, int t, int oldl, int oldt) {
    if (callbackProcs[CB_SCROLL_CHANGED] != null) {
      super.onScrollChanged(l, t, oldl, oldt);
      try {
        getRuby().callMethod(callbackProcs[CB_SCROLL_CHANGED], "call" , new Object[]{l, t, oldl, oldt});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onScrollChanged(l, t, oldl, oldt);
    }
  }

  public boolean onSetAlpha(int alpha) {
    if (callbackProcs[CB_SET_ALPHA] != null) {
      super.onSetAlpha(alpha);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_SET_ALPHA], "call" , alpha, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onSetAlpha(alpha);
    }
  }

  public void onSizeChanged(int w, int h, int oldw, int oldh) {
    if (callbackProcs[CB_SIZE_CHANGED] != null) {
      super.onSizeChanged(w, h, oldw, oldh);
      try {
        getRuby().callMethod(callbackProcs[CB_SIZE_CHANGED], "call" , new Object[]{w, h, oldw, oldh});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onSizeChanged(w, h, oldw, oldh);
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

  public void onWindowFocusChanged(boolean hasWindowFocus) {
    if (callbackProcs[CB_WINDOW_FOCUS_CHANGED] != null) {
      super.onWindowFocusChanged(hasWindowFocus);
      try {
        getRuby().callMethod(callbackProcs[CB_WINDOW_FOCUS_CHANGED], "call" , hasWindowFocus);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onWindowFocusChanged(hasWindowFocus);
    }
  }

  public void onWindowVisibilityChanged(int visibility) {
    if (callbackProcs[CB_WINDOW_VISIBILITY_CHANGED] != null) {
      super.onWindowVisibilityChanged(visibility);
      try {
        getRuby().callMethod(callbackProcs[CB_WINDOW_VISIBILITY_CHANGED], "call" , visibility);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onWindowVisibilityChanged(visibility);
    }
  }

  public boolean onCheckIsTextEditor() {
    if (callbackProcs[CB_CHECK_IS_TEXT_EDITOR] != null) {
      super.onCheckIsTextEditor();
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_CHECK_IS_TEXT_EDITOR], "call" , Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onCheckIsTextEditor();
    }
  }

  public android.view.inputmethod.InputConnection onCreateInputConnection(android.view.inputmethod.EditorInfo outAttrs) {
    if (callbackProcs[CB_CREATE_INPUT_CONNECTION] != null) {
      super.onCreateInputConnection(outAttrs);
      try {
        return (android.view.inputmethod.InputConnection) getRuby().callMethod(callbackProcs[CB_CREATE_INPUT_CONNECTION], "call" , outAttrs, android.view.inputmethod.InputConnection.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return null;
      }
    } else {
      return super.onCreateInputConnection(outAttrs);
    }
  }

  public void onFinishTemporaryDetach() {
    if (callbackProcs[CB_FINISH_TEMPORARY_DETACH] != null) {
      super.onFinishTemporaryDetach();
      try {
        getRuby().callMethod(callbackProcs[CB_FINISH_TEMPORARY_DETACH], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onFinishTemporaryDetach();
    }
  }

  public boolean onKeyPreIme(int keyCode, android.view.KeyEvent event) {
    if (callbackProcs[CB_KEY_PRE_IME] != null) {
      super.onKeyPreIme(keyCode, event);
      try {
        return (Boolean) getRuby().callMethod(callbackProcs[CB_KEY_PRE_IME], "call" , new Object[]{keyCode, event}, Boolean.class);
      } catch (RaiseException re) {
        re.printStackTrace();
        return false;
      }
    } else {
      return super.onKeyPreIme(keyCode, event);
    }
  }

  public void onStartTemporaryDetach() {
    if (callbackProcs[CB_START_TEMPORARY_DETACH] != null) {
      super.onStartTemporaryDetach();
      try {
        getRuby().callMethod(callbackProcs[CB_START_TEMPORARY_DETACH], "call" );
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onStartTemporaryDetach();
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

  public void onDisplayHint(int hint) {
    if (callbackProcs[CB_DISPLAY_HINT] != null) {
      super.onDisplayHint(hint);
      try {
        getRuby().callMethod(callbackProcs[CB_DISPLAY_HINT], "call" , hint);
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onDisplayHint(hint);
    }
  }

  public void onVisibilityChanged(android.view.View changedView, int visibility) {
    if (callbackProcs[CB_VISIBILITY_CHANGED] != null) {
      super.onVisibilityChanged(changedView, visibility);
      try {
        getRuby().callMethod(callbackProcs[CB_VISIBILITY_CHANGED], "call" , new Object[]{changedView, visibility});
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    } else {
      super.onVisibilityChanged(changedView, visibility);
    }
  }
}
