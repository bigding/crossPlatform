package util;

public class Key {
    public static final int VK_LBUTTON = 0x01;              //鼠标左键
    public static final int VK_RBUTTON = 0x02;              //鼠标右键
    public static final int VK_CANCEL = 0x03;               //Control-break processing
    public static final int VK_MBUTTON = 0x04;              //鼠标中键
    public static final int VK_XBUTTON1 = 0x05;             //X1 mouse button
    public static final int VK_XBUTTON2 = 0x06;             //X2 mouse button
    public static final int VK_BACK = 0x08;                 //BACKSPACE key
    public static final int VK_TAB = 0x09;
    // 0x0A-0B    Reserved
    public static final int VK_CLEAR = 0x0C;                //clear
    public static final int VK_RETURN = 0x0D;
    // 0x0E-0F   Undefined
    public static final int VK_SHIFT = 0x10;
    public static final int VK_CONTROL = 0x11;               //ctrl
    public static final int VK_MENU = 0x12;                  //alt
    public static final int VK_PAUSE = 0x13;                 //pause key
    public static final int VK_CAPITAL = 0x14;               //Caps lock
    //IME Kana mode/IME Hanguel mode (maintained for compatibility; use VK_HANGUL)/IME Hangul mode
    public static final int VK_KANA_HANGUEL_HANGUL = 0x15;
    // 0x16  Undefined
    public static final int VK_JUNJA = 0x17;                //IME Junja mode
    public static final int VK_FINAL = 0x18;                //IME final mode
    public static final int VK_HANJA_KANJI = 0x19;        //IME Hanja/Kanji mode
    // 0x1A  Undefined
    public static final int VK_ESCAPE = 0x1B;
    public static final int VK_CONVERT = 0x1C;              //IME convert
    public static final int VK_NONCONVERT = 0x1D;           //IME nonconvert
    public static final int VK_ACCEPT = 0x1E;               //IME accept
    public static final int VK_MODECHANGE = 0x1F;           //IME mode change request
    public static final int VK_SPACE = 0x20;                 //SPACEBAR
    public static final int VK_PRIOR = 0x21;                 //PAGE UP key
    public static final int VK_NEXT = 0x22;                 //PAGE DOWN key
    public static final int VK_END = 0x23;
    public static final int VK_HOME = 0x24;
    public static final int VK_LEFT = 0x25;
    public static final int VK_UP = 0x26;
    public static final int VK_RIGHT = 0x27;
    public static final int VK_DOWN = 0x28;
    public static final int VK_SELECT = 0x29;
    public static final int VK_PRINT = 0x2A;
    public static final int VK_EXECUTE = 0x2B;
    public static final int VK_SNAPSHOT = 0x2C;
    public static final int VK_INSERT = 0x2D;
    public static final int VK_DELETE = 0x2E;
    public static final int VK_HELP = 0x2F;
    public static final int VK_0 = 0x30;
    public static final int VK_1 = 0x31;
    public static final int VK_2 = 0x32;
    public static final int VK_3 = 0x33;
    public static final int VK_4 = 0x34;
    public static final int VK_5 = 0x35;
    public static final int VK_6 = 0x36;
    public static final int VK_7 = 0x37;
    public static final int VK_8 = 0x38;
    public static final int VK_9 = 0x39;
    // 0x3A-40  Undefined
    public static final int VK_A = 0x41;
    public static final int VK_B = 0x42;
    public static final int VK_C = 0x43;
    public static final int VK_D = 0x44;
    public static final int VK_E = 0x45;
    public static final int VK_F = 0x46;
    public static final int VK_G = 0x47;
    public static final int VK_H = 0x48;
    public static final int VK_I = 0x49;
    public static final int VK_J = 0x4A;
    public static final int VK_K = 0x4B;
    public static final int VK_L = 0x4C;
    public static final int VK_M = 0x4D;
    public static final int VK_N = 0x4E;
    public static final int VK_O = 0x4F;
    public static final int VK_P = 0x50;
    public static final int VK_Q = 0x51;
    public static final int VK_R = 0x52;
    public static final int VK_S = 0x53;
    public static final int VK_T = 0x54;
    public static final int VK_U = 0x55;
    public static final int VK_V = 0x56;
    public static final int VK_W = 0x57;
    public static final int VK_X = 0x58;
    public static final int VK_Y = 0x59;
    public static final int VK_Z = 0x5A;
    public static final int VK_LWIN = 0x5B;                 //Left Windows key (Natural keyboard)
    public static final int VK_RWIN = 0x5C;                 //Right Windows key (Natural keyboard)
    public static final int VK_APPS = 0x5D;                 //Applications key (Natural keyboard)
    // 0x5E Reserved
    public static final int VK_SLEEP = 0x5F;                //Computer Sleep key
    public static final int VK_NUMPAD0 = 0x60;              //Numeric keypad 0 key
    public static final int VK_NUMPAD1 = 0x61;              //Numeric keypad 1 key
    public static final int VK_NUMPAD2 = 0x62;              //Numeric keypad 2 key
    public static final int VK_NUMPAD3 = 0x63;              //Numeric keypad 3 key
    public static final int VK_NUMPAD4 = 0x64;              //Numeric keypad 4 key
    public static final int VK_NUMPAD5 = 0x65;              //Numeric keypad 5 key
    public static final int VK_NUMPAD6 = 0x66;              //Numeric keypad 6 key
    public static final int VK_NUMPAD7 = 0x67;              //Numeric keypad 7 key
    public static final int VK_NUMPAD8 = 0x68;              //Numeric keypad 8 key
    public static final int VK_NUMPAD9 = 0x69;              //Numeric keypad 9 key
    public static final int VK_MULTIPLY = 0x6A;             //Multiply key
    public static final int VK_ADD = 0x6B;
    public static final int VK_SEPARATOR = 0x6C;
    public static final int VK_SUBTRACT = 0x6D;
    public static final int VK_DECIMAL = 0x6E;
    public static final int VK_DIVIDE = 0x6F;
    public static final int VK_F1 = 0x70;
    public static final int VK_F2 = 0x71;
    public static final int VK_F3 = 0x72;
    public static final int VK_F4 = 0x73;
    public static final int VK_F5 = 0x74;
    public static final int VK_F6 = 0x75;
    public static final int VK_F7 = 0x76;
    public static final int VK_F8 = 0x77;
    public static final int VK_F9 = 0x78;
    public static final int VK_F10 = 0x79;
    public static final int VK_F11 = 0x7A;
    public static final int VK_F12 = 0x7B;
    public static final int VK_F13 = 0x7C;
    public static final int VK_F14 = 0x7D;
    public static final int VK_F15 = 0x7E;
    public static final int VK_F16 = 0x7F;
    public static final int VK_F17 = 0x80;
    public static final int VK_F18 = 0x81;
    public static final int VK_F19 = 0x82;
    public static final int VK_F20 = 0x83;
    public static final int VK_F21 = 0x84;
    public static final int VK_F22 = 0x85;
    public static final int VK_F23 = 0x86;
    public static final int VK_F24 = 0x87;
    // 0x88-8F  Unassigned
    public static final int VK_NUMLOCK = 0x90;
    public static final int VK_SCROLL = 0x91;
    // 0x92-96  OEM specific
    // 0x97-9F  Unassigned
    public static final int VK_LSHIFT = 0xA0;               //Left SHIFT key
    public static final int VK_RSHIFT = 0xA1;               //Right SHIFT key
    public static final int VK_LCONTROL = 0xA2;             //Left CONTROL key
    public static final int VK_RCONTROL = 0xA3;             //Right CONTROL key
    public static final int VK_LMENU = 0xA4;                //Left MENU key
    public static final int VK_RMENU = 0xA5;                //Right MENU key
    public static final int VK_BROWSER_BACK = 0xA6;         //Browser Back key
    public static final int VK_BROWSER_FORWARD = 0xA7;      //Browser Forward key
    public static final int VK_BROWSER_REFRESH = 0xA8;      //Browser Refresh key
    public static final int VK_BROWSER_STOP = 0xA9;         //Browser Stop key
    public static final int VK_BROWSER_SEARCH = 0xAA;       //Browser Search key
    public static final int VK_BROWSER_FAVORITES = 0xAB;    //Browser Favorites key
    public static final int VK_BROWSER_HOME = 0xAC;          //Browser Start and Home key
    public static final int VK_VOLUME_MUTE = 0xAD;           //Volume Mute key
    public static final int VK_VOLUME_DOWN = 0xAE;           //Volume Down key
    public static final int VK_VOLUME_UP = 0xAF;             //Volume Up key
    public static final int VK_MEDIA_NEXT_TRACK = 0xB0;      //Next Track key
    public static final int VK_MEDIA_PREV_TRACK = 0xB1;      //Previous Track key
    public static final int VK_MEDIA_STOP = 0xB2;            //Stop Media key
    public static final int VK_MEDIA_PLAY_PAUSE = 0xB3;     //Play/Pause Media key
    public static final int VK_LAUNCH_MAIL = 0xB4;          //Start Mail key
    public static final int VK_LAUNCH_MEDIA_SELECT = 0xB5;  //Select Media key
    public static final int VK_LAUNCH_APP1= 0xB6;             //Start Application 1 key
    public static final int VK_LAUNCH_APP2 = 0xB7;            //Start Application 2 key
    // 0xB8-B9  Reserved
    public static final int VK_OEM_1 = 0xBA;                 //Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the ';:' key
    public static final int VK_OEM_PLUS = 0xBB;             //For any country/region, the '+' key
    public static final int VK_OEM_COMMA = 0xBC;            //For any country/region, the ',' key
    public static final int VK_OEM_MINUS = 0xBD;            //For any country/region, the '-' key
    public static final int VK_OEM_PERIOD = 0xBE;           //For any country/region, the '.' key
    public static final int VK_OEM_2 = 0xBF;                 //Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the '/?' key
    public static final int VK_OEM_3 = 0xC0;                 //Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the '`~' key
    // 0xC1-D7  Reserved
    // 0xD8-DA  Unassigned
    public static final int VK_OEM_4 = 0xDB;                 //Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the '[{' key
    public static final int VK_OEM_5 = 0xDC;                 //Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the '\|' key
    public static final int VK_OEM_6 = 0xDD;                 //Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the ']}' key
    public static final int VK_OEM_7 = 0xDE;                 //Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the 'single-quote/double-quote' key
    public static final int VK_OEM_8 = 0xDF;                 //Used for miscellaneous characters; it can vary by keyboard.
    // 0xE0  Reserved
    // 0xE1  OEM_SPECIFIC
    public static final int VK_OEM_102 = 0xE2;               //Either the angle bracket key or the backslash key on the RT 102-key keyboard
    // 0xE3-E4  OEM specific
    public static final int VK_PROCESSKEY = 0xE5;           //IME PROCESS key
    // 0xE6  OEM specific
    public static final int VK_PACKET = 0xE7;               //Used to pass Unicode characters as if they were keystrokes. The VK_PACKET key is the low word of a 32-bit Virtual Key value used
                                                                // for non-keyboard input methods. For more information, see Remark in KEYBDINPUT, SendInput, WM_KEYDOWN, and WM_KEYUP
    // 0xE8  Unassigned
    // 0xE9-F5  OEM specific
    public static final int VK_ATTN = 0xF6;
    public static final int VK_CRSEL = 0xF7;
    public static final int VK_EXSEL = 0xF8;
    public static final int VK_EREOF = 0xF9;
    public static final int VK_PLAY = 0xFA;
    public static final int VK_ZOOM = 0xFB;
    public static final int VK_NONAME = 0xFC;               //Reserved
    public static final int VK_PA1 = 0xFD;                  //PA1 key
    public static final int VK_OEM_CLEAR = 0xFE;           //Clear key
}
