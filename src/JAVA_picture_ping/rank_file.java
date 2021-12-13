package JAVA_picture_ping;

import java.io.*;

public class rank_file {
        int[] m = new int[20];
        int n= 0;

        public void input() throws IOException {
            String str;
            BufferedReader readin = new BufferedReader(new FileReader("rank_file.txt"));
            while ((str = readin.readLine()) != null)
                m[n++] = Integer.parseInt(str);
            readin.close();
        }
        public void output(int x){
            m[n++]=x;
            for(int i=0;i<n-1;i++)
                if (m[n-1]<m[i]){
                    for(int j=n-1;j>i;j--) m[j]=m[j-1];
                    m[i]=x;
                    break;
                }
            try (BufferedWriter out = new BufferedWriter(new FileWriter("rank_file.txt"))) {
                for(int i=0;i<n;i++) {
                    out.write(m[i] + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

