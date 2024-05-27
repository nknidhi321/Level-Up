// https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/

class Solution {
    
    public List<String> removeSubfolders(String[] folders) {
        Arrays.sort(folders); // Sorting so that parent folder comes first in asc order
        Set<String> set = new HashSet<>();
        
        for(String folder : folders) {
            String[] directories = folder.split("/");
            String soFarPath = "";
            boolean wasSubfolder = false;
            for(String currDirectory : directories) {
                // Ex : if you split on "/" for string "/a"
                // it will get split into "" and "a"
                // So discard empty string
                if(currDirectory.equals("")) continue; 
                
                soFarPath += "/" + currDirectory;
                
                // For each soFarPath check if parent path exists or not
                // If that exists, its a subfolder
                if(set.contains(soFarPath)) {
                    wasSubfolder = true;
                    break;
                }
            }
            if(!wasSubfolder) {
                set.add(folder); // adding parent folder
            }
        }
        
        return new ArrayList<>(set);
    }
    
}
