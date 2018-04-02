package com.datacrawler.service.model.intel.com;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.datacrawler.common.util.JsoupUtil;

public class DownloadGoodsIMGs {

    public static void main(String[] args) throws Exception {
        String url = "https://www.intel.cn/content/www/cn/zh/products/processors/core/view-all.html";
        Document doc = JsoupUtil.getDocument(url);
        
        // Elements ele = doc.select("firure.blade-image");
        
        Elements divEles = doc.select("div.item-wrap2");
        
        System.out.println(divEles.toString());
        for(Element child: divEles){
            printSectionDetails(child);
        }
        
        
        // System.out.println(doc.toString());
    }
    
    /*function to print section details : prints direct child header name and img src of the section node */
    private static void printSectionDetails(Element section){
        Elements arrChildren = section.children();
        
       // System.out.println(arrChildren); // 获取class为item-wrap2的div的所有子节点
        
        for(Element child : arrChildren){
            // System.out.println(child.toString());

            if(child.tagName().equals("a")){            // 只循环前两个子节点<a  >
                Elements children = child.children();   // 获取到 <figure class="blade-image"> 
                // System.out.println("==================================");
                //System.out.println(children.toString());
                for(Element grandchild : children){
                    if(grandchild.tagName().equals("figure")){
                        Elements spanchildren = grandchild.children(); // 获取到所有<figure class="blade-image"> 下的<span>
                        //System.err.println(spanchildren);
                        for(Element spanchild : spanchildren){
                            Elements imgchild = spanchild.children(); // <span data-src="/content....
                            System.out.println(imgchild.attr("data-src"));
                        }
                    }
                }
            }
        }
    }

}
