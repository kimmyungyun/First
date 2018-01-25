package com.example.user.first;

/**
 * Created by 명윤 on 2018-01-25.
 */

//Dot_Show 에서 이미지 밑에 붙여 줄 초성, 중성, 모음 텍스트 로 바꾸는 부분.
public class fromDottoKR {
    public fromDottoKR(){
    }
    public char conv(byte Bit){
        switch(Bit){
            case 0b00001000:
                return 'ㄱ';
            case 0b00001001:
                return 'ㄴ';
            case 0b00001010:
                return 'ㄷ';
            case 0b00010000:
                return 'ㄹ';
            case 0b00010001:
                return 'ㅁ';
            case 0b00011000:
                return 'ㅂ';
            case 0b00100000:
                return 'ㅅ';
            case 0b00101000:
                return 'ㅈ';
            case 0b00110000:
                return 'ㅊ';
            case 0b00001011:
                return 'ㅋ';
            case 0b00010011:
                return 'ㅌ';
            case 0b00011001:
                return 'ㅍ';
            case 0b00011010:
                return 'ㅎ';
            //case 0b00100000:    된소리.
            //   return ' ';
        }
        return ' ';
    }
}
