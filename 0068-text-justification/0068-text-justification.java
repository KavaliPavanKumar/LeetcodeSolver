import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;
        
        while (index < words.length) {
            int totalChars = words[index].length();
            int last = index + 1;
            
            while (last < words.length) {
                if (totalChars + 1 + words[last].length() > maxWidth) break;
                totalChars += 1 + words[last].length();
                last++;
            }
            
            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;
            
            if (last == words.length || diff == 0) {
                // Left-justified (last line or single word in a line)
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) builder.append(' ');
                }
                while (builder.length() < maxWidth) {
                    builder.append(' ');
                }
            } else {
                // Fully justified (middle lines)
                int spaces = (maxWidth - totalChars) / diff;
                int extraSpaces = (maxWidth - totalChars) % diff;
                
                for (int i = index; i < last - 1; i++) {
                    builder.append(words[i]);
                    builder.append(' ');
                    for (int j = 0; j < spaces + (i - index < extraSpaces ? 1 : 0); j++) {
                        builder.append(' ');
                    }
                }
                builder.append(words[last - 1]); // Append last word
            }
            
            result.add(builder.toString());
            index = last;
        }
        
        return result;
    }
}