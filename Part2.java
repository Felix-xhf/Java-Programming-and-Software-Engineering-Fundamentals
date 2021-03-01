
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    public int howMany (String a, String b) {
        int startIndex = 0;
        int count = 0;
        int currIndex = 0;
        while (currIndex != -1) {
            currIndex = b.indexOf (a, startIndex);
            startIndex = currIndex + a.length();
            ++count;
        }
        return count-1;
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) return "";
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex<taaIndex)){
            minIndex = tgaIndex;
        }else{
            minIndex = taaIndex;
        }
        
        if(minIndex ==-1 || (tagIndex !=-1 && tagIndex <minIndex)){
            minIndex = tagIndex;
        }
        
        if(minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex+3);
    }
    
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff % 3 ==0){
                return currIndex;
            }else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return -1;
    }
    
    public void countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        String currentGene = "";
        while (true){
            currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            count = count + 1;
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
        }
    }
    
    public void testhowMany(){
        String a = "GAA";
        String b = "ATGAACGAATTGAATC";
        System.out.println(a);
        System.out.println(b);
        int count = howMany(a,b);
        
        System.out.println(count);
    }
}
