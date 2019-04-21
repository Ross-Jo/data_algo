#include <stdio.h>

int mystrlen(char *str) {
    int len = 0;
    while(*str++) {len++;}
    return len;
}

int mystrlen2(char* str) {
    int len = 0;
    while(str[len++]);
    return len-1;
}

void mystrcpy(char* src, char* dest) {
    while(*dest++ == *src++);
}

int mystrcmp(char *s1, char* s2) {
    int idx = 0;
    while(s1[idx]){
        if(s1[idx]!=s2[idx]) break;
        idx++;
    }
    if(s1[idx] > s2[idx]) return 1;
    else if(s1[idx] < s2[idx]) return -1;
    return 0;
}

int myatoi(char* sNum) {
    int val = 0;
    char ch;
    int digit;
    int sign = 1;
    
    if (*sNum == '-') {
        sign = -1;
        sNum++;
    }
    
    while(ch = *sNum++) {
        digit = ch - '0';
        val = val * 10 + digit;
    }
    
    return val * sign;
}

int myatoi16(char* sNum) {
    int val = 0;
    char ch;
    int digit;
    int sign = 1;
    
    if (*sNum == '-') {
        sign = -1;
        sNum++;
    }
    
    while(ch = *sNum++) {
        if(ch > '9') digit = ch - 'a' + 10; // (16진수 a~e처리)
        else digit = ch - '0';
        val = val * 16 + digit;
    }
    
    return val * sign;
}

void myitoa(int iNum, char* sNum) {
    int val;
    int idx = 0;
    while(iNum) {
        val = iNum % 10 + '0';
        sNum[idx++] = val;
        iNum = iNum / 10;
    }
    int iLen = idx / 2;
    for (int i=0; i<iLen; i++) { // 뒤집어진 숫자 반전
        int t = sNum[i];
        sNum[i] = sNum[idx-1-i];
        sNum[idx-1-i] = t;
    }
    sNum[idx] = 0;
}
