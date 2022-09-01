try{
	//var maxHeight = 200;
	$(function(){
	
	    $("ul.dropdown li").hover(function(){
	    
	        $(this).addClass("hover");
	        $('ul:first',this).css('visibility', 'visible');
	    
	    }, function(){
	    
	        $(this).removeClass("hover");
	        $('ul:first',this).css('visibility', 'hidden');
	    
	    });
	    
	    $("ul.dropdown li ul li:has(ul)").find("a:first").append(' <i class="glyphicon glyphicon-hand-right pullRight"> </i> ');
	    
	    $('.dropdown li').parent().hover(function() {
            try {
	        var menu = $(this).find("ul");
	        var menupos = $(menu).offset();               
	        if (menupos.left + menu.width() > $(window).width()) {
	            var newpos = -151;            
	            menu.css({ left: newpos });    
	        }
            } catch (e) {
            }
	    });
	    /*
	    $(".dropdown li ul li").hover(function() {
	        
	         var $container = $(this),
	             $list = $container.find("ul"),
	             $anchor = $container.find("a"),
	             height = $list.height() * 1.1,       // make sure there is enough room at the bottom
	             multiplier = height / maxHeight;     // needs to move faster if list is taller
	        
	        // need to save height here so it can revert on mouseout            
	        $container.data("origHeight", $container.height());
	        
	        // so it can retain it's rollover color all the while the dropdown is open
	        $anchor.addClass("hover");
	        
	        // make sure dropdown appears directly below parent list item    
	        $list
	            .show()
	            .css({
	                paddingTop: $container.data("origHeight")
	            });
	        
	        // don't do any animation if list shorter than max
	        if (multiplier > 1) {
	            $container
	                .css({
	                    height: maxHeight,
	                    overflow: "hidden"
	                })
	                .mousemove(function(e) {
	                    var offset = $container.offset();
	                    var relativeY = ((e.pageY - offset.top) * multiplier) - ($container.data("origHeight") * multiplier);
	                    if (relativeY > $container.data("origHeight")) {
	                        $list.css("top", -relativeY + $container.data("origHeight"));
	                    };
	                });
	        }
	        
	    }, function() {
	    
	        var $el = $(this);
	        
	        // put things back to normal
	        $el
	            .height($(this).data("origHeight"))
	            .find("ul")
	            .css({ top: 0 })
	            .hide()
	            .end()
	            .find("a")
	            .removeClass("hover");
	    
	    });
	    // Add down arrow only to menu items with submenus
	    $(".dropdown > li:has('ul')").each(function() {
	        $(this).find("a:first").append("<img src='images/down-arrow.png' />");
	    });
	    */
	});
}
catch(e){}