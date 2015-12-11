package datasign.calculadoraip;

import java.util.ArrayList;

/**
 * Created by jean on 10/12/15.
 */
public class Conversor {
    private static Conversor conversor = new Conversor();
    public static Conversor newInstance(){
        if(conversor != null){
            return conversor;
        }else {
            return new Conversor();
        }
    }

    public String paraBinario(int num){
       return String.format("%8s", Integer.toBinaryString((byte)num & 0xFF)).replace(' ', '0');
    }

    public int paraDecimal(String bin){
        return Integer.parseInt(bin, 2);
    }

    public String getMascaraDecimal(String binMask){
        String maskDecimal = "";
        for (String bin : binMask.split("\\.")){
            maskDecimal += paraDecimal(bin);
            maskDecimal += ".";
        }
        return maskDecimal.substring(0, maskDecimal.length()-1);
    }

    public String toBinAddr(String addr){
        String binAddr = "";
        for(String s : addr.split("\\.")){
            binAddr+=paraBinario(Integer.valueOf(s));
            binAddr+=".";
        }
        return binAddr.substring(0, binAddr.length() - 1);
    }

    public String getRedeAddr(String binIpAddr, String binMaskAddr){
        String[] binIp = getMatrizAddr(binIpAddr);
        String[] binMask = getMatrizAddr(binMaskAddr);
        String rede = "";
        for (int x = 0; x < 4; x++){
            rede += Integer.parseInt(binIp[x], 2) & Integer.parseInt(binMask[x], 2);
            rede += ".";
        }
        return rede.substring(0, rede.length()-1);
    }

    public int getQuantidadeHosts(String binAddrMask){
        int qtd = 0;
        for (int x = 0; x < binAddrMask.length(); x++){
            if(binAddrMask.charAt(x) == '0'){
                qtd++;
            }
        }
        return (int)Math.pow(2, qtd);
    }

    public String notAddr(String binAddr){
        String notBinAddr = "";
        for(int x = 0; x < binAddr.length(); x++){
            if(binAddr.charAt(x) == '0'){
                notBinAddr += "1";
            }else {
                notBinAddr += "0";
            }
        }
        return addPontos(notBinAddr);
    }

    public String getBroadCastAddr(String binIpAddr, String binMaskAddr){
        String[] binIp = getMatrizAddr(binIpAddr);
        String[] binMask = getMatrizAddr(notAddr(binMaskAddr));
        String rede = "";
        for (int x = 0; x < 4; x++){
            rede += Integer.parseInt(binIp[x], 2) | Integer.parseInt(binMask[x], 2);
            rede += ".";
        }
        return rede.substring(0, rede.length() - 1);
    }

    public String getMascara(int bits){
        String mascaraEmBits = "";
        for (int x = 0; x < 32; x++){
            if(x < bits){
                mascaraEmBits += "1";
            }else {
                mascaraEmBits += "0";
            }
        }
        return mascaraEmBits;
    }

    public String addPontos(String binAddress){
        return binAddress.substring(0, 8) + "." + binAddress.substring(binAddress.length()-24, binAddress.length()-16) + "." + binAddress.substring(binAddress.length()-16, binAddress.length()-8) + "." + binAddress.substring(binAddress.length()-8, binAddress.length());
    }

    public String[] getMatrizAddr(String binAddr){
        return binAddr.split("\\.");
    }

    public ArrayList<String> getBits(){
        ArrayList<String> bits = new ArrayList<>();
        bits.add("-");
        bits.add("1");
        bits.add("2");
        bits.add("3");
        bits.add("4");
        bits.add("5");
        bits.add("6");
        bits.add("7");
        bits.add("8");
        bits.add("9");
        bits.add("10");
        bits.add("11");
        bits.add("12");
        bits.add("13");
        bits.add("14");
        bits.add("15");
        bits.add("16");
        bits.add("17");
        bits.add("18");
        bits.add("19");
        bits.add("20");
        bits.add("21");
        bits.add("22");
        bits.add("23");
        bits.add("24");
        bits.add("25");
        bits.add("26");
        bits.add("27");
        bits.add("28");
        bits.add("29");
        bits.add("30");
        bits.add("31");
        bits.add("32");
        return bits;
    }
}