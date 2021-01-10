package Others;

class ManagerForTime {
    private long beforeTime, afterTime;

    public void setBeforeTime() {
        this.beforeTime = System.currentTimeMillis();
    }

    public void setAfterTime() {
        this.afterTime = System.currentTimeMillis();
    }

    public long getTime() {
        return (afterTime - beforeTime)/1000;
    }
}

public class ssafyProblem5 {

    public static void main(String[] args) {
        ManagerForTime managerForTime = new ManagerForTime();

        managerForTime.setBeforeTime();
        long resultOne = makeFiboOne(50);
        managerForTime.setAfterTime();
        System.out.println(resultOne + ", time : " + managerForTime.getTime());

        managerForTime.setBeforeTime();
        long resultTwo = makeFiboTwo(50);
        managerForTime.setAfterTime();
        System.out.println(resultTwo + ", time : " + managerForTime.getTime());

        managerForTime.setBeforeTime();
        long resultThree = makeFiboThree(50);
        managerForTime.setAfterTime();
        System.out.println(resultThree + ", time : " + managerForTime.getTime());
    }

    private static long makeFiboThree(int n) {
        long[] fiboMemo = new long[n + 1];

        for(int i = 1; i < fiboMemo.length; i++) {
            if(i <= 2) fiboMemo[i] = 1;
            else fiboMemo[i] = fiboMemo[i-1] + fiboMemo[i-2];
        }

        return fiboMemo[n];
    }

    private static long makeFiboTwo(int n) {
        long[] fiboMemo = new long[n + 1];
        fiboMemo[1] = 1; fiboMemo[2] = 1;
        return makeFiboTwoSub(n, fiboMemo);
    }

    private static long makeFiboTwoSub(int n, long[] fiboMemo) {
        if(fiboMemo[n] != 0)
            return fiboMemo[n];
        else
            return fiboMemo[n] = makeFiboTwoSub(n-1, fiboMemo) + makeFiboTwoSub(n-2, fiboMemo);
    }

    private static long makeFiboOne(int n) {
        if(n <= 2) return 1;
        return makeFiboOne(n-1) + makeFiboOne(n-2);
    }
}
