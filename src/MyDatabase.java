import java.io.*;
import java.util.*;
import java.lang.*;

public class MyDatabase {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		String fileName="PHARMA_TRIALS_1000B.csv";
		
		// This will reference one line at a time
        String line = null;
        RandomAccessFile raf;
		String x;
		
            // FileReader reads text files in the default encoding.
//            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            bufferedReader.readLine();
            HashMap<Integer, Long> id = new HashMap<Integer, Long>();
            company compan=new company();
            company drug=new company();
            Numb trials=new Numb();
            Numb patients=new Numb();
            Numb dosage=new Numb();
            Flt reading=new Flt();
            company doub_blind=new company();
            company cont_study=new company();
            company govt_fund=new company();
            company fda_approv=new company();

            
            System.out.println("Choose any option in the following list");
            System.out.println("1. Parse the input file to binary");
            System.out.println("2. Choose a field to query");
            Scanner sc=new Scanner(System.in);
            int inpt=sc.nextInt();
            if(inpt==1 || inpt==2){
            try{
            	 System.out.println("Enter the input file path:");
             	 Scanner sc2=new Scanner(System.in);
            	 String s=sc2.nextLine();
               	 FileReader fr=new FileReader(s);
         	     BufferedReader br=new BufferedReader(fr);
	       	     br.readLine();
	       	     String st;
	       	     
	       	     System.out.println("Enter the output file path:");
	       	     Scanner sc3=new Scanner(System.in);
	       	     String out=sc3.nextLine();
	       	     x=out;
	       	     FileOutputStream fileout=new FileOutputStream(out);
	       	     ObjectOutputStream os=new ObjectOutputStream (fileout);
	       	     String file=out;
	       	     raf=new RandomAccessFile(out,"rw");;
	 	     
	       	     
//	       	     File fout = new File("out.txt");
//        	RandomAccessFile raf=new RandomAccessFile(fout,"rw");
            while((line = br.readLine()) != null) {

            	st=line;
                String[] parts=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                parts[1]=parts[1].replaceAll("\"", "");
                long fileLength=raf.length();
                
                raf.seek(fileLength);   
                raf.writeInt(Integer.parseInt(parts[0]));
                raf.writeByte(parts[1].length());
                raf.writeBytes(parts[1]);
                raf.writeBytes(parts[2]);
                raf.writeShort(Short.parseShort(parts[3]));
                raf.writeShort(Short.parseShort(parts[4]));
                raf.writeShort(Short.parseShort(parts[5]));
	            raf.writeFloat(Float.parseFloat(parts[6]));
	            byte boo=0;
	            if(parts[7].equalsIgnoreCase("TRUE"))
	            	boo= (byte) (boo|8);
	            if(parts[8].equalsIgnoreCase("TRUE"))
	            	boo= (byte) (boo|4);
	            if(parts[9].equalsIgnoreCase("TRUE"))
	            	boo= (byte) (boo|2);
	            if(parts[10].equalsIgnoreCase("TRUE"))
	            	boo= (byte) (boo|1);
	            raf.writeByte(boo);

	             String double_blind="FALSE";
				 String controlled_study="FALSE";
				 String govt_funded="FALSE"; 
				 String fda_approved="FALSE";
				 
				 if((boo&8)==8)double_blind="TRUE";			 
				 if((boo&4)==4)controlled_study="TRUE";			 
				 if((boo&2)==2)govt_funded="TRUE";			 
				 if((boo&1)==1)fda_approved="TRUE";
            
				 id.put(Integer.parseInt(parts[0]), fileLength);
				 compan.put(parts[1], fileLength);
				 drug.put(parts[2], fileLength);
				 trials.put(Short.parseShort(parts[3]), fileLength);
				 patients.put(Short.parseShort(parts[4]), fileLength);
				 dosage.put(Short.parseShort(parts[5]), fileLength);
				 reading.put(Float.parseFloat(parts[6]), fileLength);
				 doub_blind.put(parts[7], fileLength);
				 cont_study.put(parts[8], fileLength);
				 govt_fund.put(parts[9], fileLength);
				 fda_approv.put(parts[10], fileLength);
            } 
            
            File file1=new File("id.ndx");
            File file2=new File("company.ndx");
            File file3=new File("drug_id.ndx");
            File file4=new File("trials.ndx");
            File file5=new File("patients.ndx");
            File file6=new File("dosage_mg.ndx");
            File file7=new File("reading.ndx");
            File file8=new File("double_blind.ndx");
            File file9=new File("controlled_study.ndx");
            File file10=new File("govt_funded.ndx");
            File file11=new File("fda_approved.ndx");
            
            FileOutputStream fos=new FileOutputStream(file1);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(id);
            oos.flush();
            
            fos=new FileOutputStream(file2);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(compan);
            oos.flush();
            
            fos=new FileOutputStream(file3);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(drug);
            oos.flush();
            
            fos=new FileOutputStream(file4);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(trials);
            oos.flush();
            
            fos=new FileOutputStream(file5);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(patients);
            oos.flush();
            
            fos=new FileOutputStream(file6);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(dosage);
            oos.flush();
            
            fos=new FileOutputStream(file7);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(reading);
            oos.flush();
            
            fos=new FileOutputStream(file8);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(doub_blind);
            oos.flush();
            
            fos=new FileOutputStream(file9);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(cont_study);
            oos.flush();
            
            fos=new FileOutputStream(file10);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(govt_fund);
            oos.flush();
            
            fos=new FileOutputStream(file11);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(fda_approv);
            oos.flush();
            }
            finally{}
            }
            System.out.println("Input csv file has been successfully converted to binary file ");
            if(inpt==2){
            System.out.println("Enter the datafile path(binary) which you would wish to query");
            Scanner s4=new Scanner(System.in);
            String filenumb=s4.nextLine();
            RandomAccessFile r=new RandomAccessFile(filenumb,"rw");
		    System.out.println("enter the number corresponding to field \n");
		    System.out.println("1.ID \n 2.Company \n 3.Drug_ID \n 4.Trails \n 5.Patients \n 6.dosage \n 7.reading \n 8.double_blind\n 9.controlled_study\n 10.govt_funded\n 11.fda_approved \n");
		    Scanner s3 = new Scanner(System.in);
		    int option=s3.nextInt();
		    
		    switch(option){
		    case 1: 
		    	System.out.println("enter the value:");
		    	int given_id=s3.nextInt();
		    	System.out.println("Select one of the following options: \n 1.= \n 2.> \n 3.< \n 4.>= \n 5.<= \n 6. != \n");
		    	int given_operator=s3.nextInt();
		    	for(Integer key:id.keySet()){
		    		if(given_operator==1)
		    		{
		    			if(key==given_id){
		    				long temp=id.get(key);
		    				r.seek(temp);
		    				System.out.print(r.readInt());
		    				Byte r1=new Byte(r.readByte());
		    				int r2=r1.intValue();
		    				byte[] r3=new byte[r2];
		    				r.readFully(r3);
		    				String st=new String(r3,"UTF-8");
		    				r3=new byte[6];
		    				r.readFully(r3);
		    				String st1=new String(r3,"UTF-8");
		    				System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");
		    				Byte r4=new Byte(r.readByte());
		    				if((r4&8)==8)
		    					System.out.print("True"+ " ");
		    				else
		    					System.out.print("False"+ " ");
		    				if((r4&4)==4)
		    					System.out.print("True"+ " ");
		    				else
		    					System.out.print("False"+ " ");
		    				if((r4&2)==2)
		    					System.out.print("True"+ " ");
		    				else
		    					System.out.print("False"+ " ");
		    				if((r4&1)==1)
		    					System.out.println("True"+ " ");
		    				else
		    					System.out.println("False"+ " ");
		    			}
		    		}
		    		if(given_operator==2)
		    		{
		    			if(key>given_id)
		    			{
		    				long temp=id.get(key);r.seek(temp);System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");
		    			}
		    		}
		    		if(given_operator==3)
		    			if(key<given_id){
		    				long temp=id.get(key);r.seek(temp);System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");
		    			}
		    		if(given_operator==4)
		    		{
		    			if(key<=given_id)
		    			{
		    				long temp=id.get(key);r.seek(temp);System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");
		    			}
		    		}
		    		if(given_operator==5)
		    		{
		    			if(key>=given_id)
		    			{
		    				long temp=id.get(key);r.seek(temp);System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");
		    			}
		    		}
		    		if(given_operator==6)
		    		{
		    			if(key!=given_id)
		    			{
		    				long temp=id.get(key);r.seek(temp);System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");
		    			}
		    		}
		    	}
		    break;
		    
		    case 2:
		    	System.out.println("enter the value:");
		    	Scanner t = new Scanner(System.in);
		    	String given_compname=t.nextLine();	
		    
		    	System.out.println("Select one of the following options: \n 1.= \n 2.> \n 3.< \n 4.>= \n 5.<= \n 6. != \n");
		    	int given_operator2=t.nextInt();
		    	
		    	for(String key:compan.keySet())
		    	{
		    		if(given_operator2==1)
		    		{
		    			if(key.equals(given_compname))
		    			{
		    				List<Long> temp=compan.get(key);
		    				for(int i=0;i<temp.size();i++)
		    				{
		    					r.seek(temp.get(i));
		    					System.out.print(r.readInt());
		    					Byte r1=new Byte(r.readByte());
			    				int r2=r1.intValue();
			    				byte[] r3=new byte[r2];
			    				r.readFully(r3);
			    				String st=new String(r3,"UTF-8");
			    				r3=new byte[6];
			    				r.readFully(r3);
			    				String st1=new String(r3,"UTF-8");
			    				System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");
			    				Byte r4=new Byte(r.readByte());
			    				if((r4&8)==8)
			    					System.out.print("True"+ " ");
			    				else
			    					System.out.print("False"+ " ");
			    				if((r4&4)==4)
			    					System.out.print("True"+ " ");
			    				else
			    					System.out.print("False"+ " ");
			    				if((r4&2)==2)
			    					System.out.print("True"+ " ");
			    				else
			    					System.out.print("False"+ " ");
			    				if((r4&1)==1)
			    					System.out.println("True"+ " ");
			    				else
			    					System.out.println("False"+ " ");
		    				}
		    			}
		    		}
		    		
		    	
		    		if(given_operator2==2)
		    		{
		    			if(key.compareTo(given_compname)>0){
		    				List<Long> temp=compan.get(key);
		    				for(int i=0;i<temp.size();i++)
		    				{
		    					r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");
		    				}
				    	}
				    }
				    if(given_operator2==3)
				    {
				    	if(key.compareTo(given_compname)<0){
				    		List<Long> temp=compan.get(key);
		    				for(int i=0;i<temp.size();i++)
		    				{
		    					r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");
		    				}
				    	}
				    }
				    if(given_operator2==4)
				    {
				    	if(key.compareTo(given_compname)>0 || key.compareTo(given_compname)==0){
				    		List<Long> temp=compan.get(key);
		    				for(int i=0;i<temp.size();i++)
		    				{
		    					r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");
		    				}
				    	}
				    }
				    if(given_operator2==5)
				    {
				    	if(key.compareTo(given_compname)<0||key.compareTo(given_compname)==0){
				    		List<Long> temp=compan.get(key);
		    				for(int i=0;i<temp.size();i++)
		    				{
		    					r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");
		    				}
				    	}
				    }
				    if(given_operator2==6)
				    {
				    	if(!(key.equals(given_compname))){
				    		List<Long> temp=compan.get(key);
		    				for(int i=0;i<temp.size();i++)
		    				{
		    					r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");
		    				}
				    	}
				    }
				 }
		    	break;
		    
		    case 3:
		    	System.out.println("Select one of the following options: \n 1.= \n 2.> \n 3.< \n 4.>= \n 5.<= \n 6. != \n");
		    	int given_operator3=s3.nextInt();
		    	Scanner sc10=new Scanner(System.in);
		    	System.out.println("enter the value:");
		    	String given_drug=sc10.nextLine();
		    	for(String key:drug.keySet()){
		    		if(given_operator3==1){
		    			if(key.equals(given_drug)){
		    				List<Long> temp=drug.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.println(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}
		    			}
		    		}
		    		if(given_operator3==2){
		    			if(key.compareTo(given_drug)>0){
		    				List<Long> temp=drug.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}
		    			}
		    		}
		    		if(given_operator3==3){
		    			if(key.compareTo(given_drug)<0){
		    				List<Long> temp=drug.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}
		    			}
		    		}
		    		if(given_operator3==4){
		    			if(key.compareTo(given_drug)>0||key.compareTo(given_drug)==0){
		    				List<Long> temp=drug.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}
		    			}
		    		}
		    		if(given_operator3==5){
		    			if(key.compareTo(given_drug)<0||key.compareTo(given_drug)==0){
		    				List<Long> temp=drug.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}
		    			}
		    		}
		    		if(given_operator3==6){
		    			if(!(key.equals(given_drug))){
		    				List<Long> temp=drug.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}
		    			}
		    		}
		    	}
		    	break;
		    case 4:
		    	System.out.println("Select one of the following options: \n 1.= \n 2.> \n 3.< \n 4.>= \n 5.<= \n 6. != \n");
		    	int given_operator4=s3.nextInt();
		    	System.out.println("enter the value:");
		    	int given_trials=s3.nextInt();
		    	for(int key:trials.keySet()){
		    		if(given_operator4==1){
		    			if(key==given_trials){List<Long> temp=trials.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator4==2){
		    			if(key>given_trials){List<Long> temp=trials.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator4==3){
		    			if(key<given_trials){List<Long> temp=trials.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator4==4){
		    			if(key>=given_trials){List<Long> temp=trials.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator4==5){
		    			if(key<=given_trials){List<Long> temp=trials.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator4==6){
		    			if(key!=given_trials){List<Long> temp=trials.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    	}
		    	break;
		    case 5:
		    	System.out.println("Select one of the following options: \n 1.= \n 2.> \n 3.< \n 4.>= \n 5.<= \n 6. != \n");
		    	int given_operator5=s3.nextInt();
		    	System.out.println("enter the value:");
		    	int given_patients=s3.nextInt();
		    	for(int key:patients.keySet()){
		    		if(given_operator5==1){
		    			if(key==given_patients){List<Long> temp=patients.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator5==2){
		    			if(key>given_patients){List<Long> temp=patients.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator5==3){
		    			if(key<given_patients){List<Long> temp=patients.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator5==4){
		    			if(key>=given_patients){List<Long> temp=patients.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator5==5){
		    			if(key<=given_patients){List<Long> temp=patients.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator5==6){
		    			if(key!=given_patients){List<Long> temp=patients.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    	}
		    	break;
		    case 6:
		    	System.out.println("Select one of the following options: \n 1.= \n 2.> \n 3.< \n 4.>= \n 5.<= \n 6. != \n");
		    	int given_operator6=s3.nextInt();
		    	System.out.println("enter the value:");
		    	int given_dosage=s3.nextInt();
		    	for(int key:dosage.keySet()){
		    		if(given_operator6==1){
		    			if(key==given_dosage){List<Long> temp=dosage.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator6==2){
		    			if(key>given_dosage){List<Long> temp=dosage.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator6==3){
		    			if(key<given_dosage){List<Long> temp=dosage.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator6==4){
		    			if(key>=given_dosage){List<Long> temp=dosage.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator6==5){
		    			if(key<=given_dosage){List<Long> temp=dosage.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator6==6){
		    			if(key!=given_dosage){List<Long> temp=dosage.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    	}
		    	break;
		    case 7:
		    	System.out.println("Select one of the following options: \n 1.= \n 2.> \n 3.< \n 4.>= \n 5.<= \n 6. != \n");
		    	int given_operator7=s3.nextInt();
		    	System.out.println("enter the value:");
		    	float given_reading=s3.nextFloat();
		    	for(Float key:reading.keySet()){
		    		if(given_operator7==1){
		    			if(key==given_reading){List<Long> temp=reading.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator7==2){
		    			if(key>given_reading){List<Long> temp=reading.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator7==3){
		    			if(key<given_reading){List<Long> temp=reading.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator7==4){
		    			if(key>=given_reading){List<Long> temp=reading.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator7==5){
		    			if(key<=given_reading){List<Long> temp=reading.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    		if(given_operator7==6){
		    			if(key!=given_reading){List<Long> temp=reading.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}	
		    			}
		    		}
		    	}
		    	break;
		    case 8:
		    	Scanner s8=new Scanner(System.in);
		    	System.out.println("Enter the value");
		    	String given_db=s8.nextLine();
		    	for(String key:doub_blind.keySet()){
		    		if(key.equalsIgnoreCase(given_db)){List<Long> temp=doub_blind.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}
		    	}
		    	}
		    	break;
		    case 9:
		    	Scanner s5=new Scanner(System.in);
		    	System.out.println("Enter the value");
		    	String given_cs=s5.nextLine();
		    	for(String key:cont_study.keySet()){
		    		if(key.equalsIgnoreCase(given_cs)){List<Long> temp=cont_study.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}
		    	}
		    	}
		    	break;	
		    case 10:
		    	Scanner s6=new Scanner(System.in);
		    	System.out.println("Enter the value");
		    	String given_gf=s6.nextLine();
		    	for(String key:govt_fund.keySet()){
		    		if(key.equalsIgnoreCase(given_gf)){List<Long> temp=govt_fund.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}
		    	}
		    	}
		    	break;	
		    case 11:
		    	Scanner s7=new Scanner(System.in);
		    	System.out.println("Enter the value");
		    	String given_fa=s7.nextLine();
		    	for(String key:fda_approv.keySet()){
		    		if(key.equalsIgnoreCase(given_fa)){List<Long> temp=fda_approv.get(key);for(int i=0;i<temp.size();i++){r.seek(temp.get(i));System.out.print(r.readInt());Byte r1=new Byte(r.readByte());int r2=r1.intValue();byte[] r3=new byte[r2];r.readFully(r3);String st=new String(r3,"UTF-8");r3=new byte[6];r.readFully(r3);String st1=new String(r3,"UTF-8");System.out.print(" "+r1+" "+st+" "+st1+" "+r.readShort()+ " " +r.readShort()+ " "+r.readShort()+ " " +r.readFloat()+ " ");Byte r4=new Byte(r.readByte());if((r4&8)==8)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&4)==4)System.out.print("True"+ " ");else	System.out.print("False"+ " ");if((r4&2)==2)System.out.print("True"+ " ");else System.out.print("False"+ " ");if((r4&1)==1)System.out.println("True"+ " ");else System.out.println("False"+ " ");}
		    	}
		    	}
		    	break;	
		    default:
		    	break;
		    }
}
	}
	}