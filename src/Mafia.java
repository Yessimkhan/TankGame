import java.util.Scanner;

public class Mafia {
    public static void main(String[] args) {
        String [] cards={"Мафия","Мафия","Жител","Жител","Жител","Полиция","Доктор"};
        //Әрбір адамның Личностьтың сақта.
        String [] people=new String[7];
        //Дауыс саның орнатыңыз.
        int [] poll=new int[7];
        int [] random={8,8,8,8,8,8,8};
        int a=0;//Оны кез келген жерге қойыныз.
        int nvNum=1;//Доктор улы немесе антидотты қолданатының белгіленіз.
        boolean flag=true;
        do{
            int count=1;//Егерде бірдей кездейсоқ сан болса, онда оган +1.
            int j=(int)(Math.random()*7);
            for(int i=0;i<random.length;i++){
                if(j==random[i]){
                    count++;
                }

            }
            if(count==1){//Кездейсоқ бірдей сан болмаған кезде
                random[a]=j;
                a++;
            }
        }while(a!=7);
        //Идентификацияны кездейсоқ түрде орнатыңыз.
        for(int i=0;i<cards.length;i++){
            people[i]=cards[random[i]];
        }
        //Әрбір адамның личносын жазып шығар.
        for(int i=0;i<people.length;i++){
            System.out.println((i+1)+"Нөмерының личносы ол："+people[i]+"\t");
        }
        int yuYan=0;//police
        int nvWu=0;//dok

        for(int i=0;i<people.length;i++){
            if(people[i].equals("Полиция")){
                yuYan=i;
            }else if(people[i].equals("Доктор")){
                nvWu=i;
            }
        }
        do{
            Scanner in=new Scanner(System.in);
            System.out.println("Күн батты,көзінді жум");
            System.out.println("Мафия өлтыретын адамның нөмеры:");
            int num = in.nextInt();
            System.out.println("Полиция кімнің личносын карайсыз");
            if(!people[yuYan].equals("Умер")){
                int num1 = in.nextInt();
                System.out.println(num1+" Нөмерының личносы ол "+people[num1-1]);
            }

            System.out.println("Доктор адамды қутқарасынба талданыз(y/n) ");
            int b=1;
            int num4=0;
            if(!people[nvWu].equals("Умер")){
                if(nvNum != 0){
                    String ans=in.next();
                    if(ans.equals("n")){
                        b=1;
                    }else{
                        b=0;
                        nvNum--;
                    }
                    System.out.println(" Адамды умен өлтыресызба талданыз:(y/n)");
                    if(nvNum!=0){
                        String ans1=in.next();
                        if(ans1.equals("y")){
                            System.out.println("Қай нөмерды умен өлтыретының жазыныз:");
                            num4=in.nextInt();
                            b=2;
                            nvNum--;
                        }
                    }
                }
            }
            if(b==0){
                System.out.println("Бүгінгі түн тыныш болды");
            }else if(b==1){
                System.out.println(num+"Нөмер умер");
                people[num-1]="Умер";

            }else{
                System.out.println(num+"Нөмер умер");
                people[num-1]="Умер";
                System.out.println(num4+"Нөмер умер");
                people[num4-1]="Умер";
            }
            //Дауыс саның орнатыныз
            for(int i=0;i<people.length;i++){
                if(!people[i].equals("Умер")){
                    System.out.println((i+1)+"Номерының талдауы:");
                    int num2=in.nextInt();
                    switch(num2-1){
                        case 0:
                            poll[0]++;
                            break;
                        case 1:
                            poll[1]++;
                            break;
                        case 2:
                            poll[2]++;
                            break;
                        case 3:
                            poll[3]++;
                            break;
                        case 4:
                            poll[4]++;
                            break;
                        case 5:
                            poll[5]++;
                            break;
                        case 6:
                            poll[6]++;
                            break;

                    }
                }
            }
            //Максималды дауыстар санына оралыңыз
            int max=poll[0];
            int num3=0;
            for(int i=0;i<poll.length;i++){
                if(poll[i]>max){
                    max=poll[i];
                    num3=i;
                }
            }
            //Голоспен шығатын адам ол
            System.out.println(num3+1+"Номерды голоспен өлтырды");
            people[num3]="Умер";
            for(int i=0;i<people.length;i++){
                System.out.println((i+1)+"тың номеры ол："+people[i]+"\t");
            }
            poll=new int[7];
            int end1=0;
            int end2=0;
            for(int i=0;i<people.length;i++){
                if(people[i].equals("Мафия")){
                    end1++;
                }else if(people[i].equals("Умир")){
                }else{
                    end2++;
                }

            }

            if(end1==0){
                System.out.println("Ойын бытты, Жител WIN !");
                flag=false;
            }else if(end1<=end2){
                System.out.println("Ойын жалғасат.");
            }else{
                System.out.println("Ойын бытты, Мафия WIN !");
                flag=false;
            }
        }while(flag);

    }
}