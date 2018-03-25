package util;


import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 编码参照手册 https://msdn.microsoft.com/en-us/library/dd375731(v=vs.85).aspx
 */
public class Key {

    public static final int VK_SLEEP = 0x5F;    //Computer Sleep key
    public static final int VK_SELECT = 0x29;    //SELECT key

    public static Object getKeyText(int var0) {
        switch(var0) {
            case 0:
                return KeyEvent.CHAR_UNDEFINED;
            case 1:
                return KeyEvent.VK_ESCAPE;
            case 2:
                return KeyEvent.VK_1;
            case 3:
                return KeyEvent.VK_2;
            case 4:
                return KeyEvent.VK_3;
            case 5:
                return KeyEvent.VK_4;
            case 6:
                return KeyEvent.VK_5;
            case 7:
                return KeyEvent.VK_6;
            case 8:
                return KeyEvent.VK_7;
            case 9:
                return KeyEvent.VK_8;
            case 10:
                return KeyEvent.VK_9;
            case 11:
                return KeyEvent.VK_0;
            case 12:
                return KeyEvent.VK_MINUS;
            case 13:
                return KeyEvent.VK_EQUALS;
            case 14:
                return KeyEvent.VK_BACK_SPACE;
            case 15:
                return KeyEvent.VK_TAB;
            case 16:
                return KeyEvent.VK_Q;
            case 17:
                return KeyEvent.VK_W;
            case 18:
                return KeyEvent.VK_E;
            case 19:
                return KeyEvent.VK_R;
            case 20:
                return KeyEvent.VK_T;
            case 21:
                return KeyEvent.VK_Y;
            case 22:
                return KeyEvent.VK_U;
            case 23:
                return KeyEvent.VK_I;
            case 24:
                return KeyEvent.VK_O;
            case 25:
                return KeyEvent.VK_P;
            case 26:
                return KeyEvent.VK_OPEN_BRACKET;
            case 27:
                return KeyEvent.VK_CLOSE_BRACKET;
            case 28:
                return KeyEvent.VK_ENTER;
            case 29:
                return KeyEvent.VK_CONTROL;
            case 30:
                return KeyEvent.VK_A;
            case 31:
                return KeyEvent.VK_S;
            case 32:
                return KeyEvent.VK_D;
            case 33:
                return KeyEvent.VK_F;
            case 34:
                return KeyEvent.VK_G;
            case 35:
                return KeyEvent.VK_H;
            case 36:
                return KeyEvent.VK_J;
            case 37:
                return KeyEvent.VK_K;
            case 38:
                return KeyEvent.VK_L;
            case 39:
                return KeyEvent.VK_SEMICOLON ;
            case 40:
                return KeyEvent.VK_QUOTE ;
            case 41:
                return KeyEvent.VK_BACK_QUOTE;
            case 42:
                return KeyEvent.VK_SHIFT;
            case 43:
                return KeyEvent.VK_BACK_SLASH;
            case 44:
                return KeyEvent.VK_Z;
            case 45:
                return KeyEvent.VK_X;
            case 46:
                return KeyEvent.VK_C;
            case 47:
                return KeyEvent.VK_V;
            case 48:
                return KeyEvent.VK_B;
            case 49:
                return KeyEvent.VK_N;
            case 50:
                return KeyEvent.VK_M;
            case 51:
                return KeyEvent.VK_COMMA;
            case 52:
                return KeyEvent.VK_PERIOD;
            case 53:
                return KeyEvent.VK_SLASH;
            case 56:
                return KeyEvent.VK_CONTEXT_MENU;
            case 57:
                return KeyEvent.VK_SPACE;
            case 58:
                return KeyEvent.VK_CAPS_LOCK;
            case 59:
                return KeyEvent.VK_F1;
            case 60:
                return KeyEvent.VK_F2;
            case 61:
                return KeyEvent.VK_F3;
            case 62:
                return KeyEvent.VK_F4;
            case 63:
                return KeyEvent.VK_F5;
            case 64:
                return KeyEvent.VK_F6;
            case 65:
                return KeyEvent.VK_F7;
            case 66:
                return KeyEvent.VK_F8;
            case 67:
                return KeyEvent.VK_F9;
            case 68:
                return KeyEvent.VK_F10;
            case 69:
                return KeyEvent.VK_NUM_LOCK;
            case 70:
                return KeyEvent.VK_SCROLL_LOCK;
            case 83:
                return Toolkit.getProperty("AWT.separator", "NumPad ,");
            case 87:
                return KeyEvent.VK_F11;
            case 88:
                return KeyEvent.VK_F12;
            case 91:
                return KeyEvent.VK_F13;
            case 92:
                return KeyEvent.VK_F14;
            case 93:
                return KeyEvent.VK_F15;
            case 99:
                return KeyEvent.VK_F16;
            case 100:
                return KeyEvent.VK_F17;
            case 101:
                return KeyEvent.VK_F18;
            case 102:
                return KeyEvent.VK_F19;
            case 103:
                return KeyEvent.VK_F20;
            case 104:
                return KeyEvent.VK_F21;
            case 105:
                return KeyEvent.VK_F22;
            case 106:
                return KeyEvent.VK_F23;
            case 107:
                return KeyEvent.VK_F24;
            case 112:
                return KeyEvent.VK_KATAKANA;
            case 115:
                return KeyEvent.VK_UNDERSCORE;
            case 119:
                return Toolkit.getProperty("AWT.furigana", "Furigana");
            case 121:
                return KeyEvent.VK_KANJI;
            case 123:
                return KeyEvent.VK_HIRAGANA;
            case 125:
                return Toolkit.getProperty("AWT.yen", Character.toString('¥'));
            case 3639:
                return KeyEvent.VK_COMMA;
            case 3653:
                return KeyEvent.VK_PAUSE;
            case 3655:
                return KeyEvent.VK_HOME;
            case 3657:
                return KeyEvent.VK_PAGE_UP;
            case 3663:
                return KeyEvent.VK_END;
            case 3665:
                return KeyEvent.VK_PAGE_DOWN ;
            case 3666:
                return KeyEvent.VK_INSERT;
            case 3667:
                return KeyEvent.VK_DELETE;
            case 3675:
                return KeyEvent.VK_META;
            case 3677:
                return KeyEvent.VK_CONTEXT_MENU;
            case 57360:
                return Toolkit.getProperty("AWT.previous", "Previous");
            case 57369:
                return Toolkit.getProperty("AWT.next", "Next");
            case 57376:
                return Toolkit.getProperty("AWT.mute", "Mute");
            case 57377:
                return Toolkit.getProperty("AWT.app_calculator", "App Calculator");
            case 57378:
                return Toolkit.getProperty("AWT.play", "Play");
            case 57380:
                return KeyEvent.VK_STOP;
            case 57388:
                return Toolkit.getProperty("AWT.eject", "Eject");
            case 57390:
                return Toolkit.getProperty("AWT.voldn", "Volume Down");
            case 57392:
                return Toolkit.getProperty("AWT.volup", "Volume Up");
            case 57394:
                return Toolkit.getProperty("AWT.homepage", "Browser Home");
            case 57404:
                return Toolkit.getProperty("AWT.app_music", "App Music");
            case 57416:
                return KeyEvent.VK_UP;
            case 57419:
                return KeyEvent.VK_LEFT;
            case 57420:
                return KeyEvent.VK_CLEAR;
            case 57421:
                return KeyEvent.VK_RIGHT;
            case 57424:
                return KeyEvent.VK_DOWN;
            case 57438:
                return Toolkit.getProperty("AWT.power", "Power");
            case 57439:
                return VK_SLEEP;
            case 57443:
                return Toolkit.getProperty("AWT.wake", "Wake");
            case 57444:
                return Toolkit.getProperty("AWT.app_pictures", "App Pictures");
            case 57445:
                return Toolkit.getProperty("AWT.search", "Browser Search");
            case 57446:
                return Toolkit.getProperty("AWT.favorites", "Browser Favorites");
            case 57447:
                return Toolkit.getProperty("AWT.refresh", "Browser Refresh");
            case 57448:
                return KeyEvent.VK_STOP;
            case 57449:
                return Toolkit.getProperty("AWT.forward", "Browser Forward");
            case 57450:
                return Toolkit.getProperty("AWT.back", "Browser Back");
            case 57452:
                return Toolkit.getProperty("AWT.app_mail", "App Mail");
            case 57453:
                return VK_SELECT;
            case 65396:
                return Toolkit.getProperty("AWT.sun_open", "Sun Open");
            case 65397:
                return Toolkit.getProperty("AWT.sun_help", "Sun Help");
            case 65398:
                return Toolkit.getProperty("AWT.sun_props", "Sun Props");
            case 65399:
                return Toolkit.getProperty("AWT.sun_front", "Sun Front");
            case 65400:
                return Toolkit.getProperty("AWT.sun_stop", "Sun Stop");
            case 65401:
                return Toolkit.getProperty("AWT.sun_again", "Sun Again");
            case 65403:
                return Toolkit.getProperty("AWT.sun_cut", "Sun Cut");
            case 65404:
                return Toolkit.getProperty("AWT.sun_copy", "Sun Copy");
            case 65405:
                return Toolkit.getProperty("AWT.sun_insert", "Sun Insert");
            case 65406:
                return Toolkit.getProperty("AWT.sun_find", "Sun Find");
            default:
                return Toolkit.getProperty("AWT.unknown", "Unknown") + " keyCode: 0x" + Integer.toString(var0, 16);
        }
    }
}
