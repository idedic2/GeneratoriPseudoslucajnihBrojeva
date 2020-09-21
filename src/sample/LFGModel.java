package sample;

import java.util.ArrayList;
import java.util.Random;

public class LFGModel {
    private Long[] initArray;
    private Long j = 1029L;
    private Long k = 2281L;
    private int n;
    private final double m = Math.pow(2, 32);
    Random rand = new Random();
    static ArrayList<Long> lista = new ArrayList<>();


    private void fib(int n){
        lista.add(0L);
        lista.add(1L);
        for(int i=2;i<=n;i++)
            lista.add(lista.get(i-1)+lista.get(i-2));
    }
    private boolean doesExist(Long [] array, Long value){
        for(Long element: array){
            if(element==value)
                return true;
        }
        return false;
    }
    public LFGModel(boolean random)
    {
        n = Math.toIntExact(k);
        initArray = new Long[n];

        if(random) {
            for (int i = 0; i < initArray.length; i++) {
                Long value = rand.nextLong();
                if (doesExist(initArray, value)) continue;
                initArray[i] = value;
            }
        }

        else{
            fib(n);
            for (int i = 0; i < initArray.length; i++)
                initArray[i]=lista.get(i);

        }
    }

    public void setJ(Long j) {
        this.j = j;
    }

    public void setK(Long k) {
        this.k = k;
    }

    public Long GenerateNextRandomNumber()
    {

        Long randomNumber = 0L;
        //decrement j or set to optimal
        if (j <= 1)
        {
            j = 1029L;
        }
        else
        {
            j--;
        }
        // decrement k or set to optimal
        if (k <= 1)
        {
            k = 2281L;
        }
        else
        {
            k--;
        }

        ////  apply the fibonacci formula
        //randomNumber = (Xn * (n - j) - Xn * (n - k)) % m;

        //// update the initial array at position n - k to hold the random number generated
        //initArray[n - k] = randomNumber;
        //Xn = randomNumber;

        Long firstElement = initArray[(int) (n - j)];
        Long secondElement = initArray[(int) (n - k)];

        randomNumber = (long) ((((firstElement + secondElement) % m) + m) % m);
        initArray[(int) (n - k)] = randomNumber;

        //return the generated number
        return  randomNumber;
    }
}
