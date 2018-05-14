package util;


import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 编码参照手册 https://msdn.microsoft.com/en-us/library/dd375731(v=vs.85).aspx
 */
public class Key {

    /**
     * 在KeyEvent中存在值  那就直接返回对应值
     * 如果KeyEvent不存在,那就自己定义手册中值返回
     * 如果手册中没有值,那就返回-1
     * 在程序体中对 -1 这种情况特殊处理
     * @param var0
     * @return
     */
    public static String linuxGetKeyCode(int var0) {
        String reStr = "";
        switch(var0) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                reStr = "0xff08";   //backspace
                break;
            case 9:
                reStr = "0xff09";   //tab
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                reStr = "0xff0d";   //enter
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
            case 17:
                break;
            case 18:
                break;
            case 19:
                break;
            case 20:
                reStr = "0xffe5";   //caps lock
                break;
            case 21:
                break;
            case 22:
                break;
            case 23:
                break;
            case 24:
                break;
            case 25:
                break;
            case 26:
                break;
            case 27:
                reStr = "0xff1b";   //escape
                break;
            case 28:
                break;
            case 29:
                break;
            case 30:
                break;
            case 31:
                break;
            case 32:
                reStr = "0x0020";   //space
                break;
            case 33:
                reStr = "0xff55";   //page up
                break;
            case 34:
                reStr = "0xff56";   //page down
                break;
            case 35:
                reStr = "0xff57"; //end
                break;
            case 36:
                reStr = "0xff50";   //home
                break;
            case 37:
                reStr = "0xff51";  // left arrow
                break;
            case 38:
                reStr = "0xff52";   //up arrow
                break;
            case 39:
                reStr = "0xff53";   //right arrow
                break;
            case 40:
                reStr = "0xff54";   //down arrow
                break;
            case 41:
                break;
            case 42:
                break;
            case 43:
                break;
            case 44:
                reStr = "0xff61";  //print
                break;
            case 45:
                reStr = "0xff63";   //insert
                break;
            case 46:
                reStr = "0xffff";   //delete
                break;
            case 47:
                break;
            case 48:
                reStr = "0x0030";
                break;
            case 49:
                reStr = "0x0031";
                break;
            case 50:
                reStr = "0x0032";
                break;
            case 51:
                reStr = "0x0033";
                break;
            case 52:
                reStr = "0x0034";
                break;
            case 53:
                reStr = "0x0035";
                break;
            case 54:
                reStr = "0x0036";
                break;
            case 55:
                reStr = "0x0037";
                break;
            case 56:
                reStr = "0x0038";
                break;
            case 57:
                reStr = "0x0039";
                break;
            case 58:
                break;
            case 59:
                break;
            case 60:
                break;
            case 61:
                break;
            case 62:
                break;
            case 63:
                break;
            case 64:
                break;
            case 65:
                reStr = "0x0061";  //a
                break;
            case 66:
                reStr = "0x0062";
                break;
            case 67:
                reStr = "0x0063";
                break;
            case 68:
                reStr = "0x0064";
                break;
            case 69:
                reStr = "0x0065";
                break;
            case 70:
                reStr = "0x0066";
                break;
            case 71:
                reStr = "0x0067";
                break;
            case 72:
                reStr = "0x0068";
                break;
            case 73:
                reStr = "0x0069";
                break;
            case 74:
                reStr = "0x006a";
                break;
            case 75:
                reStr = "0x006b";
                break;
            case 76:
                reStr = "0x006c";
                break;
            case 77:
                reStr = "0x006d";
                break;
            case 78:
                reStr = "0x006e";
                break;
            case 79:
                reStr = "0x006f";
                break;
            case 80:
                reStr = "0x0070";
                break;
            case 81:
                reStr = "0x0071";
                break;
            case 82:
                reStr = "0x0072";
                break;
            case 83:
                reStr = "0x0073";
                break;
            case 84:
                reStr = "0x0074";
                break;
            case 85:
                reStr = "0x0075";
                break;
            case 86:
                reStr = "0x0076";
                break;
            case 87:
                reStr = "0x0077";
                break;
            case 88:
                reStr = "0x0078";
                break;
            case 89:
                reStr = "0x0079";
                break;
            case 90:
                reStr = "0x007a";  //z
                break;
            case 91:
                reStr = "0xff67";    //menu  windows键
                break;
            case 92:
                break;
            case 93:
                break;
            case 94:
                break;
            case 95:
                break;
            case 96:
                break;
            case 97:
                break;
            case 98:
                break;
            case 99:
                break;
            case 100:
                break;
            case 101:
                break;
            case 102:
                break;
            case 103:
                break;
            case 104:
                break;
            case 105:
                break;
            case 106:
                break;
            case 107:
                break;
            case 108:
                break;
            case 109:
                break;
            case 110:
                break;
            case 111:
                break;
            case 112:
                reStr = "0xffbe";   //F1
                break;
            case 113:
                reStr = "0xffbf";
                break;
            case 114:
                reStr = "0xffc0";
                break;
            case 115:
                reStr = "0xffc1";
                break;
            case 116:
                reStr = "0xffc2";
                break;
            case 117:
                reStr = "0xffc3";
                break;
            case 118:
                reStr = "0xffc4";
                break;
            case 119:
                reStr = "0xffc5";
                break;
            case 120:
                reStr = "0xffc6";
                break;
            case 121:
                reStr = "0xffc7";
                break;
            case 122:
                reStr = "0xffc8";
                break;
            case 123:
                reStr = "0xffc9";   //F12
                break;
            case 124:
                break;
            case 125:
                break;
            case 126:
                break;
            case 127:
                break;
            case 128:
                break;
            case 129:
                break;
            case 130:
                break;


            case 160:
                reStr = "0xffe1";    //left shift
                break;
            case 161:
                reStr = "0xffe2";    //right shift
                break;
            case 162:
                reStr = "0xffe3";    //left ctrl
                break;
            case 163:
                reStr = "0xffe4";   //right ctrl
                break;


            case 186:
                reStr = "0x0026";   //ampersand  ；
                break;
            case 187:
                reStr = "0x003d";   //equals
                break;
            case 188:
                reStr = "0x002c";   // comma  ,
                break;
            case 189:
                reStr = "0x00ad";   //hyphen  -  短横线
                break;
            case 190:
                reStr = "0x002e";   //period  .
                break;
            case 191:
                reStr = "0x002f";   //slash  /
                break;
            case 192:
                reStr = "0x0060";   //grave  `
                break;
            case 193:
                break;

            case 219:
                reStr = "0x005b";   //left bracket  [
                break;
            case 220:
                reStr = "0x005c";   //backslash     \
                break;
            case 221:
                reStr = "0x005d";   //right bracket  ]
                break;
            case 222:
                reStr = "0x0027";   //puote   '
                break;
        }
        return reStr;
    }
}
