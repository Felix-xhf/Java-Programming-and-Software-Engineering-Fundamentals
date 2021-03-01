
/**
 * 在这里给出对类 AllCodon 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class AllCodon {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        // find stopCodon after startIndex in dnaStr,stopCodon maybe "TAA","TGA","TAG"
        // if no stopCodon then return -1
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
    
    public String findGene(String dna, int where){
        // find Gene(String) after where
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
    
    public int printAllGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while(true){
            String currGene = findGene(dna,startIndex);
            if(currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene,startIndex) + currGene.length();
            count = count +1;
        }
        return count;
    }
    
    public void testFindStopCodon(){
        String dna = "AATGCTAACTAGCTGACTAAT";
        int index = findStopCodon(dna,1,"TGA");
        System.out.println(index);
        index = findStopCodon(dna,1,"TAA");
        System.out.println(index);
        index = findStopCodon(dna,1,"TAG");
        System.out.println(index);
        System.out.println("----------------------------");
    }
    
    public void testfindGene(){
        String dna = "AATGCTAACTAGCTGACTAAT";
        String result = findGene(dna,1);
        System.out.println(result);
        System.out.println("----------------------------");
    }
    
    public void printAllGenes(){
        String dna = "AATGCTAACTAGCTGACTAATCATGCGTCCCTAGTGCATGCGTTAA";
        //dna ="ATGTAAGATGCCCTAGT";
        int count = printAllGenes(dna);
        System.out.println(count);
        System.out.println("----------------------------");
    }
}
