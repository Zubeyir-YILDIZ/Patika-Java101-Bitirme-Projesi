import java.util.Random;
import java.util.Scanner;
public class Main
{
    static Scanner input=new Scanner(System.in);
    static Random rnd =new Random();
    static int eksenY,eksenX,girilenY,girilenX,eylem=-3,sayacTarla=0;
    static int mayinSayisi;
    static String[][] mayin,mayinTarlasi;

    public static void main(String[] args)
    {
        mayinSayisi=(eksenY*eksenX)/4;
        System.out.print("Satır sayısı: "); eksenY=input.nextInt();
        System.out.print("Sütun sayısı: "); eksenX=input.nextInt();
        mayinTarlasi=new String[eksenY][eksenX];
        mayin=new String[eksenY][eksenX];
        tarlaOlustur();
        tarlaGoster(mayinTarlasi);
        System.out.println("----------------------------------");
        //tarlaGoster(mayin);    //mayınların konumları
        while(true)
        {
            System.out.println("İşaretlenecek satır: "); girilenY=input.nextInt();
            System.out.println("İşaretlenecek sütun: "); girilenX=input.nextInt();
            System.out.println("Bayrak koymak için '-2',Açmak için '-3' tuşlayınız "); eylem=input.nextInt();

            if(girilenY<eksenY && girilenX<eksenX)
            {
                if(eylem==-3 || eylem==-2)
                {
                    if(eylem==-2)
                    {
                        if(!mayinTarlasi[girilenY][girilenX].equals("^|"))
                        {
                            mayinTarlasi[girilenY][girilenX]="^|";
                            tarlaGoster(mayinTarlasi);
                            sayacTarla--;
                        }
                        else
                            tarlaGoster(mayinTarlasi);
                    }
                    if(eylem==-3)
                    {
                        if(mayinTarlasi[girilenY][girilenX].equals("-")||mayinTarlasi[girilenY][girilenX].equals("^|"))
                        {
                            if(mayin[girilenY][girilenX].equals("*"))
                            {
                                System.out.println("Patladın! ");
                                tarlaGoster(mayinTarlasi);
                                System.out.println("------------");
                                tarlaGoster(mayin);
                                break;
                            }
                            else
                            {
                                mayinTarlasi[girilenY][girilenX]=mayinKontrol(mayin);
                                tarlaGoster(mayinTarlasi);
                                sayacTarla--;
                            }
                        }
                        else
                            tarlaGoster(mayinTarlasi);
                    }
                    if(sayacTarla<=0)
                    {
                        if(tarlaKontrol(mayinTarlasi)>mayinSayisi)
                        {
                            sayacTarla++;
                        }
                        else
                        {
                            System.out.println("Kazandınız!!");
                            tarlaGoster(mayinTarlasi);
                            break;
                        }
                    }
                }
                else
                    System.out.println("Geçersiz Değer!");
            }
            else
                System.out.println("Geçersiz aralık!");
        }
    }
    static int tarlaKontrol(String[][]tarla)
    {
        int sayac=0;
        for(int i=0;i<eksenY;i++)
        {
            for(int j=0;j<eksenX;j++)
            {
                if(tarla[i][j].equals("-") || tarla[i][j].equals("^|"))
                {
                    sayac++;
                }
            }
        }
        return sayac;
    }
    static void tarlaOlustur()
    {
        mayinSayisi=(eksenY*eksenX)/4;
        int sayac=mayinSayisi;
        for(int i=0;i<eksenY;i++)
        {
            for(int j=0;j<eksenX;j++)
            {
                while(sayac>0)
                {
                    int rastgeleY=rastgele(eksenY);
                    int rastgeleX=rastgele(eksenX);
                    if(mayin[rastgeleY][rastgeleX]==null)
                    {
                        sayac--;
                        mayin[rastgeleY][rastgeleX]="*";
                    }
                }
                if(mayinTarlasi[i][j]==null)
                {
                    mayinTarlasi[i][j]="-";

                }
                if(mayin[i][j]==null)
                {
                    mayin[i][j]="-";
                    sayacTarla+=1;
                }
            }
        }
    }
    static int rastgele(int a)
    {
        return rnd.nextInt(0,a);
    }
    static void tarlaGoster(String[][]tarla)
    {
        for(int i=0;i<eksenY;i++)
        {
            for(int j=0;j<eksenX;j++)
            {
                System.out.print(tarla[i][j]+"  ");
            }
            System.out.println();
        }
    }
    static String mayinKontrol(String[][]tarla)
    {
        boolean sol,sag,ust,alt,solUC,sagUC,solAC,sagAC;
        int cevreMayin=0;

        if(girilenY-1>=0 && girilenY-1<eksenY)
        {
            ust=tarla[girilenY-1][girilenX].equals("*");
            if(ust)
                cevreMayin++;
        }
        if(girilenY+1>=0 && girilenY+1<eksenY)
        {
            alt = tarla[girilenY + 1][girilenX].equals("*");
            if (alt)
                cevreMayin++;
        }
        if(girilenX-1>=0 && girilenX-1<eksenX)
        {
            sol=tarla[girilenY][girilenX-1].equals("*");
            if(sol)
                cevreMayin++;
        }

        if(girilenX+1>=0 && girilenX+1<eksenX)
        {
            sag=tarla[girilenY][girilenX+1].equals("*");
            if(sag)
                cevreMayin++;
        }
        if(girilenY-1>=0 && girilenX-1>=0 && girilenY-1<eksenY && girilenX-1<eksenX)
        {
            solUC=mayin[girilenY-1][girilenX-1].equals("*");
            if(solUC)
                cevreMayin++;
        }

        if(girilenY-1>=0 && girilenX+1>=0 && girilenY-1<eksenY && girilenX+1<eksenX)
        {
            sagUC=mayin[girilenY-1][girilenX+1].equals("*");
            if(sagUC)
                cevreMayin++;
        }

        if(girilenY+1>=0 && girilenX-1>=0 && girilenY+1<eksenY && girilenX-1<eksenX)
        {
            solAC=mayin[girilenY+1][girilenX-1].equals("*");
            if(solAC)
                cevreMayin++;
        }

        if(girilenY+1>=0 && girilenX+1>=0 && girilenY+1<eksenY && girilenX+1<eksenX)
        {
            sagAC=mayin[girilenY+1][girilenX+1].equals("*");
            if(sagAC)
                cevreMayin++;
        }
        return String.valueOf(cevreMayin);
    }
}
