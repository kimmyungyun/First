package com.example.user.first;

/**
 * Created by 종수 on 2017-12-05.
 */

public class Dot {
    /*case dafda
    0 초 중 종2
    1 초 중 종1
    2 초 중
    3 초 중2 종2
    4 초 중2 종1
    5 초 중2
    6    중 종2
    7    중  종1
    8    중
    9   중2 종2
    10   중2 종1
    11   중2
    12 초2 중 종2
    13 초2 중 종1
    14 초2 중
    15 초2 중2 종2
    16 초2 중2 종1
    17 초2 중2
     */
    int whatcase=0;
    byte cb_cho1,cb_cho2, cb_jung1, cb_jung2, cb_jong1, cb_jong2;
    //자바 생성자 초성중성 종성을 받아서 case별로 구분
    public Dot(int cb_cho, int cb_jung, int cb_jong){ // 초성 중성 종성을 받아서 점자로 출력하게해줌 cb=converbraill의 약자
        //초성이 'ㅇ'일 경우
        if(cb_cho==11) {
            whatcase = whatcase + 6;
        }
        //초성이 된소리 일 경우
        else if(cb_cho==1 || cb_cho==4 || cb_cho==8 || cb_cho==10 || cb_cho==13) {
            findcho2(cb_cho);
            whatcase=whatcase+12;
        }

        //초성이 1개인 경우
        else
            cb_cho1=findcho1(cb_cho);

        //중성이 ㅒ ㅙ ㅞ 일 경우
        if(cb_jung==3 || cb_jung==10 || cb_jung==15 || cb_jung==16) {
            whatcase = whatcase + 3;
            findjung1(cb_jung);
        }
        //중성 나머지
        else
            findjung1(cb_jung);

        //종성이 없는경우
        if(cb_jong == 0)
            whatcase = whatcase + 2;
        //종성이 1개인 경우
        else if(cb_jong == 1 || cb_jong == 4 || cb_jong == 7 || cb_jong == 8 || cb_jong == 16 || cb_jong == 17 || cb_jong == 19 || cb_jong == 21 ||
                cb_jong == 22 || cb_jong == 23 || cb_jong == 24 || cb_jong == 25 || cb_jong == 26 || cb_jong == 27)
        {
            whatcase++;
            cb_jong1=findjong1(cb_jong);
        }

        //종성이 2개인 경우
        else
            findjong2(cb_jong);
    }

    //출력시 byte로 나옴
    protected byte findcho1 (int cb_cho)
    {
        switch (cb_cho) {
            // ㄱ
            case 0:
                return 0b00010000;
            //ㄴ
            case 2:
                return 0b00110000;
            //ㄷ
            case 3:
                return 0b00011000;
            // ㄹ
            case 5:
                return 0b00000100;
            //ㅁ
            case 6:
                return 0b00100100;
            //ㅂ
            case 7:
                return 0b00010100;
            // ㅅ
            case 9:
                return 0b00000001;
            // ㅈ
            case 12:
                return 0b00010001;
            // ㅊ
            case 14:
                return 0b00000101;
            // ㅋ
            case 15:
                return 0b00111000;
            // ㅌ
            case 16:
                return 0b00101100;
            // ㅍ
            case 17:
                return 0b00110100;
            // ㅎ
            default:
                return 0b00011100;
        }

    }

    //초성 2개
    protected void findcho2 (int cb_cho)
    {
        //된소리
        cb_cho1=0b00000001;
        //찾아보자이제 쌍자음에서1빼면 그자음
        cb_cho2=findcho1(cb_cho-1);
    }

    protected void findjung1(int cb_jung){
        switch(cb_jung){
            //ㅏ
            case 0: cb_jung1=0b00101001;
                break;
            //ㅐ
            case 1: cb_jung1=0b00101110;
                break;
            //ㅑ
            case 2: cb_jung1=0b00010110;
                break;
            //ㅒ
            case 3: cb_jung1=0b00010110; cb_jung2=0b00101110;
                break;
            //ㅓ
            case 4: cb_jung1=0b00011010;
                break;
            //ㅔ
            case 5: cb_jung1=0b00110110;
                break;
            //ㅕ
            case 6: cb_jung1=0b00100101;
                break;
            //ㅖ
            case 7: cb_jung1=0b00010010;
                break;
            //ㅗ
            case 8: cb_jung1=0b00100011;
                break;
            //ㅠ
            case 17: cb_jung1=0b00110001;
                break;
            //ㅘ
            case 9: cb_jung1=0b00101011;
                break;
            //ㅛ
            case 12: cb_jung1=0b00010011;
                break;
            //ㅙ
            case 10: cb_jung1=0b00101011; cb_jung2=0b00101110;
                break;
            //ㅚ
            case 11: cb_jung1=0b00110111;
                break;
            //ㅜ
            case 13: cb_jung1=0b00110010;
                break;
            //ㅝ
            case 14: cb_jung1=0b00111010;
                break;
            //ㅞ
            case 15: cb_jung1=0b00111010; cb_jung2=0b00101110;
                break;
            //ㅟ
            case 16: cb_jung1=0b00110010; cb_jung2=0b00101110;
                break;
            //ㅡ
            case 18: cb_jung1=0b00011001;
                break;
            //ㅢ
            case 19: cb_jung1=0b00011101;
                break;
            //ㅣ
            default: cb_jung1=0b00100110;
                break;
            }
       }

    //종성이 1개일때 종성값 리턴
    protected byte findjong1 (int cb_jong){
        // ㄱ
        if(cb_jong == 1)
            return 0b00100000;
         /*   || cb_jong == 4 || cb_jong == 7 || cb_jong == 8 || cb_jong == 16 || cb_jong == 17 || cb_jong == 19 || cb_jong == 21 ||
                cb_jong == 22 || cb_jong == 23 || cb_jong == 24 || cb_jong == 25 || cb_jong == 26 || cb_jong == 27*/
            // ㄴ
        else if( cb_jong == 4 )
            return 0b00001100;
            // ㄷ
        else if( cb_jong == 7 )
            return 0b00000110;
            //ㄹ
        else if( cb_jong == 8 )
            return 0b00001000;
            //ㅁ
        else if( cb_jong == 16 )
            return 0b00001001;
            //ㅂ
        else if( cb_jong == 17 )
            return 0b00101000;
            //ㅅ
        else if( cb_jong == 19 )
            return 0b00000010;
            //ㅇ
        else if( cb_jong == 21 )
            return 0b00001111;
            //ㅈ
        else if( cb_jong == 22 )
            return 0b00100010;
            //ㅋ
        else if( cb_jong == 23 )
            return 0b00001110;
            //ㅌ
        else if( cb_jong == 24 )
            return 0b00001011;
            //ㅍ
        else if( cb_jong == 25 )
            return 0b00001101;
            //ㅎ
        else
            return 0b00000111;
    }

    //종성이 2개일때
    protected void findjong2(int cb_jong){
        switch (cb_jong) {
            //ㄲ
            case 2:
                cb_jong1=findjong1(1);
                cb_jong2=findjong1(1);
                break;
            //ㄳ
            case 3:
                cb_jong1=findjong1(1);
                cb_jong2=findjong1(19);
                break;
            //ㄵ
            case 5:
                cb_jong1=findjong1(4);
                cb_jong2=findjong1(22);
                break;
            //ㄶ
            case 6:
                cb_jong1=findjong1(4);
                cb_jong2=findjong1(27);
                break;
            //ㄺ
            case 9:
                cb_jong1=findjong1(8);
                cb_jong2=findjong1(1);
                break;
            //ㄻ
            case 10:
                cb_jong1=findjong1(8);
                cb_jong2=findjong1(16);
                break;
            //ㄼ
            case 11:
                cb_jong1=findjong1(8);
                cb_jong2=findjong1(17);
                break;
            //ㄽ
            case 12:
                cb_jong1=findjong1(8);
                cb_jong2=findjong1(19);
                break;
            //ㄾ
            case 13:
                cb_jong1=findjong1(8);
                cb_jong2=findjong1(25);
                break;
            //ㄿ
            case 14:
                cb_jong1=findjong1(8);
                cb_jong2=findjong1(26);
                break;
            //ㅀ
            case 15:
                cb_jong1=findjong1(8);
                cb_jong2=findjong1(27);
                break;
            //ㅄ
            case 18:
                cb_jong1=findjong1(17);
                cb_jong2=findjong1(19);
                break;
            //ㅆ
            case 20:
                cb_jong1=findjong1(20);
                cb_jong2=findjong1(20);
                break;

            }
        }
}
