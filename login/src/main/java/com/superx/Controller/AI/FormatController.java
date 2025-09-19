package com.superx.Controller.AI;

import org.fxmisc.richtext.InlineCssTextArea;

public class FormatController {

    public void formatAndDisplayAIResponse(InlineCssTextArea c2w_ai_area, String c2w_ai_response) {
        c2w_ai_area.clear();
        
        for (String c2w_ai_line : c2w_ai_response.split("\n")) {
            c2w_ai_line = c2w_ai_line.trim();
            
            if (c2w_ai_line.isEmpty()) {
                c2w_ai_area.appendText("\n");
                continue;
            }
            
            // Format headers (lines ending with :)
            if (c2w_ai_line.endsWith(":") && !c2w_ai_line.contains("*")) {
                c2w_ai_area.appendText(c2w_ai_line + "\n");
                c2w_ai_area.setStyle(c2w_ai_area.getLength() - c2w_ai_line.length() - 1, 
                                   c2w_ai_area.getLength(),
                                   "-fx-font-weight: bold; -fx-font-size: 14px; -fx-fill:rgb(255, 255, 255);");
                continue;
            }
            
            // Format bullet points
            if (c2w_ai_line.startsWith("*")) {
                String clean = c2w_ai_line.replaceFirst("\\*+", "").trim();
                c2w_ai_area.appendText("• " + clean + "\n");
                c2w_ai_area.setStyle(c2w_ai_area.getLength() - clean.length() - 2, 
                                   c2w_ai_area.getLength(),
                                   "-fx-fill:rgb(255, 255, 255); -fx-font-size: 14px;");
                continue;
            }
            
            // Regular text
            c2w_ai_area.appendText(c2w_ai_line + "\n");
            c2w_ai_area.setStyle(c2w_ai_area.getLength() - c2w_ai_line.length() - 1, 
                               c2w_ai_area.getLength(),
                               "-fx-fill: rgb(255, 255, 255); -fx-font-size: 14px;");
        }
    }
}
