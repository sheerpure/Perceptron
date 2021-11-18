package homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class train{
    static float learnrate;
    static int line = 0;
    static int stop;
    static int det[] = new int[2];
    train(float learn ,int s){
        learnrate = learn;
        stop = s;
        Train();

    }


    public static int sizeofdata() {


        line = 0;
        try {
            File file = new File("2ring.txt");

            FileReader fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);
            while (br.readLine() != null) {
                line++;
            }
        }

        catch (IOException e) {System.out.println(e);}



        return line;
    }

    public static int sgn(float value){
        if(value >=0){
            return det[0];
        }
        else
            return det[1];
    }

    public static void rand(int test[], int Train[], int testtime, int traintime){
        Random ran = new Random();
        boolean repeat = false;
        int temp;
        int emp[] = new int[sizeofdata()];
        for(int i=0;i<sizeofdata();i++){
            emp[i] = i;
        }
        for(int i=0;i<200;i++){
            int n1 = ran.nextInt(sizeofdata());
            int n2 = ran.nextInt(sizeofdata());
            temp = emp[n1];
            emp[n1] = emp[n2];
            emp[n2] = temp;
        }

        for (int i = 0; i < traintime; i++) {
            Train[i] = emp[i];
        }
        int k = traintime;
        for(int i = 0;i<testtime;i++){
            test[i] = emp[k];
            k++;
        }




    }

    public static void cal(float traindata[][], float w[], int trainexp[], int traintime, float learnrate){
        int n = 0;
        int totaltime = 0;
        float value = 0;
        int time = traintime;
        while(totaltime<time){

            if(totaltime>stop){
                break;
            }
            if(n>=traintime){
                n = n-traintime;

            }
            for(int j=0;j<3;j++){
                value = value + traindata[n][j]*(w[j]);
            }

            int e = sgn(value);
            if(e < trainexp[n]){

                for(int i=0;i<3;i++){
                    w[i] = w[i] + learnrate*traindata[n][i];
                }

                time = time + (n+1) ;
                n++;
                totaltime++;
            }
            else if(e>trainexp[n]){

                for(int i=0;i<3;i++){
                    w[i] = w[i]-learnrate*traindata[n][i];
                }

                time = time + (n+1) ;
                n++;
                totaltime++;
            }
            else{

                n++;
                totaltime++;
            }

            value = 0;

        }


    }

    public static void detexp(int exp[]){


        for(int i=1;i<sizeofdata();i++){
            if(exp[i]>exp[i-1]){
                det[0] = exp[i];
                det[1] = exp[i-1];
            }
            else if(exp[i]<exp[i-1]){
                det[0] = exp[i-1];
                det[1] = exp[i];
            }

        }




    }

    public static void gettraindata(int traintime, float traindata[][], int Train[], float data[][], int trainexp[], int exp[]){//�敺�鞈��
        for(int i=0;i<traintime;i++){
            for(int j=0;j<3;j++){
                int a = Train[i];
                traindata[i][j] = data[a][j];

            }

        }


        for(int i = 0;i<traintime;i++){
            int a = Train[i];
            trainexp[i] = exp[a];
        }
    }

    public static int  traincorrectrate(float traindata[][],int trainexp[],int traintime,float weight[]){
        float value = 0;
        int traincor = 0;
        int testcor = 0;
        for(int i=0;i<traintime;i++){
            for(int j=0;j<3;j++){
                value = value + traindata[i][j]*weight[j];

            }
            if(sgn(value) == trainexp[i]){

                traincor++;
                System.out.print(traincor+" ");
            }
            value = 0;
        }

        return((traincor*100/traintime));



    }
    public static int testcorrectrate(float testdata[][],int testexp[],int testtime,float weight[]){
        float value = 0;
        int testcor = 0;
        for(int i=0;i<testtime;i++){
            for(int j=0;j<3;j++){
                value = value + testdata[i][j]*weight[j];
            }
            if(sgn(value) == testexp[i]){
                testcor++;
            }
            value = 0;
        }
        System.out.println("testcor"+(testcor*100/testtime));
       return ((testcor*100/testtime));
    }

    public static void gettestdata(int testtime, float testdata[][], int test[], float data[][], int testexp[], int exp[]){
        for(int i=0;i<testtime;i++){
            for(int j=0;j<3;j++){
                int a = test[i];
                testdata[i][j] = data[a][j];
            }

        }
        for(int i=0;i<testtime;i++){
            int a = test[i];
            testexp[i] = exp[a];
        }
    }

    public static void Train(){
        int traintime = sizeofdata()*2/3;
        int testtime = sizeofdata()-traintime;
        float[] weight = {-1, 0, 1};
        float[][] data = new float[sizeofdata()][3];
        float[][] traindata = new float[traintime][3];
        float[][] testdata = new float[testtime][3];
        int[] test = new int[testtime];
        int[] Train = new int[traintime];
        int[] exp = new int[sizeofdata()];
        int[] trainexp = new int[traintime];
        int[] testexp = new int[testtime];



        String line, tempstring;
        String[] temparray = new String [3];
        int linenumber = 0;


        try {
            FileReader fr = new FileReader("2ring.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                tempstring = line;
                temparray = tempstring.split("\\s");
                int index = 0;
                for(int i=1;i<3;i++){
                    data[linenumber][i] = Float.parseFloat(temparray[index]);
                    index++;
                }
                exp[linenumber] = Integer.valueOf(temparray[index]);

                linenumber++;
            }
        }
        catch (IOException e) {System.out.println(e);}
        for (int i = 0; i < sizeofdata(); i++) {
            data[i][0] = -1;
        }
        detexp(exp);

        rand(test,Train,testtime,traintime);

        gettraindata(traintime,traindata,Train,data,trainexp,exp);

        gettestdata(testtime,testdata,test,data,testexp,exp);

        cal(traindata,weight,trainexp,traintime,learnrate);




        pic frame = new pic(data,weight,sizeofdata(),exp,det,traincorrectrate(traindata,trainexp,traintime,weight),testcorrectrate(testdata,testexp,testtime,weight));






    }
}
