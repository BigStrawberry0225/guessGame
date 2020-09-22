package game;

public class Solution {
    int[] num = new int[4];
    char[] letter = new char[4];

    public Solution() {
        num[0] = (int) (Math.random()*10);

        while(true) {
            num[1] = (int) (Math.random()*10);
            if(num[1] != num[0])break;
        }

        while(true) {
            num[2] = (int) (Math.random()*10);
            if(num[2] != num[0] && num[2] != num[1])break;
        }

        while(true) {
            num[3] = (int) (Math.random()*10);
            if(num[3] != num[0] && num[3] != num[1] && num[3] != num[2])break;
        }

        //字母
        letter[0] = (char)((int)(65+Math.random()*10));

        while(true) {
            letter[1] = (char)((int)(65+Math.random()*10));
            if(letter[1] != letter[0])break;
        }

        while(true) {
            letter[2] = (char)((int)(65+Math.random()*10));
            if(letter[2] != letter[0] && letter[2] != letter[1])break;
        }

        while(true) {
            letter[3] = (char)((int)(65+Math.random()*10));
            if(letter[3] != letter[0] && letter[3] != letter[1] && letter[3] != letter[2])break;
        }
    }

    //生成数字答案
    public int[] getNumber() {
        return num;
    }

    //生成字母答案
    public char[] getLetter() {
        return letter;
    }

    //提示tip方法
    public int[] tip(int[] value) {
        int n = 0;
        int m = 0;
        for(int i = 0;i<4; i++) {
            if(value[i] == num[i]) {
                n++;
            }else {
                for(int j = 0;j<4;j++) {
                    if(j==i)continue;
                    if(value[i] == num[j]) {
                        m++;
                        break;
                    }
                }
            }
        }
        int[] arr = {n, m};
        return arr;
    }

    public int[] tip(char[] value) {
        int n = 0;
        int m = 0;
        for(int i = 0;i<4; i++) {
            if(value[i] == letter[i]) {
                n++;
            }else {
                for(int j = 0;j<4;j++) {
                    if(j==i)continue;
                    if(value[i] == letter[j]) {
                        m++;
                        break;
                    }
                }
            }
        }
        int[] arr = {n, m};
        return arr;
    }

}
