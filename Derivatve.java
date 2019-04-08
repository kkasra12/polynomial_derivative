import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Derivatve{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int level=Integer.valueOf(input.nextLine());
        String equation=input.nextLine();//"x^2+5x^1+2x^3+9x";
        input.close();
        // System.out.println(equation);
        // System.out.println(level);
        Pattern pattern=Pattern.compile("([+|-]?\\d*\\.?\\d+)?x[\\^]?([\\d]+)?");
        double[] coefficients=new double[10000];
        double[] coefficients_=new double[coefficients.length+1];
        int[] powers=new int[coefficients.length];
        int coefficients_last_index=0,power_last_index=0;
        Matcher m=pattern.matcher(equation);
        // System.out.println(equation);
        while(m.find()){
            String coop=m.group(1);
            double c;
            // System.out.println(coop);
            if(coop==null || coop.equals("+")) c=1.0;
            else c=Double.valueOf(coop);
            coefficients[coefficients_last_index++]=c;
            String pow=m.group(2);
            int p;
            if(pow==null) p=1;
            else          p=Integer.valueOf(pow);
            powers[power_last_index++]=p;
        }
        
        // for(double i:coefficients)
        // System.out.println(i);
        // System.out.println("------");
        for(int i=0;i<=coefficients_.length;i++)
            for(int j=0;j<coefficients.length;j++)
                if(powers[j]==i)
                    coefficients_[i]+=coefficients[j];
        // for(double i:coefficients_)
        // System.out.println(i);
        int i=0;
        coefficients_[coefficients_.length-1]=0;
        for(int k=0;k<level;k++)
        for(i=0;i<coefficients_.length-1;i++)
            coefficients_[i]=coefficients_[i+1]*(i+1);
        // System.out.println("----");
        // for(double j:coefficients_)
        // System.out.println(j);
        System.out.println(show_eq(coefficients_));
    }
    static int count_x(String str){
        char[] chr=str.toCharArray();
        int count=0;
        for(char c:chr)
            if(c=='x') count++;
        return count;
    }
    static String show_eq(double[] c){
        String ans="";
        boolean first_exp=true;
        for(int i=c.length-1;i>=0;i--){
            if(c[i]==0) continue;
            if(i==0){
                if(c[i]==1)       ans+="+1";
                else if(c[i]==-1) ans+="-1";
                else if(c[i]>0)   ans+="+"+format(c[i]);
                else if(c[i]<0)   ans+=format(c[i]);
            }else if(first_exp){
                if(c[i]==1)       ans+="x";
                else if(c[i]==-1) ans+="-x";
                else              ans+=format(c[i])+"x";
                if(i!=1) ans+="^"+String.valueOf(i);
                first_exp=false;
            }else{
                if(c[i]==1)       ans+="+x";
                else if(c[i]==-1) ans+="-x";
                else if(c[i]>0)   ans+="+"+format(c[i])+"x";
                else if(c[i]<0)   ans+=format(c[i])+"x";
                if(i!=1) ans+="^"+format(i);
            }
        }
        return ans;
    }

    static String format(double d) {
        if((int)d==d) return String.valueOf((int)d);
        else return String.format("%.2f", d);
    }
}
