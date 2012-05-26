package  src{
	
	/* Importing the necessary packages */
	import flash.display.*;
	import flash.net.*;
	import flash.events.*;
	import flash.filters.BlurFilter;
	import flash.text.TextField;
	import fl.transitions.Tween;
	import fl.transitions.easing.*;
	import flash.utils.Timer;
	import flash.utils.getTimer;
	
	/* Main class constructor */
	public class Main extends MovieClip {
		
		/* Define the necessary Loaders and MovieClips for the component */
		private var slider:MovieClip = new MovieClip();
		private var view:MovieClip = new MovieClip();
		private var viewItems:MovieClip = new MovieClip();
		private var info:MovieClip = new MovieClip();
		private var thumb:MovieClip = new MovieClip();
		private var thumbItems:MovieClip = new MovieClip();
		private var infoText:TextField = new TextField();
		private var textMask:MovieClip = new MovieClip();
		private var playGraphic:Loader = new Loader();
		private var pauseGraphic:Loader = new Loader();
		private var nextGraphic:Loader = new Loader();
		private var previousGraphic:Loader = new Loader();
		private var viewLoader:MovieClip = new MovieClip();
		private var viewTimer:MovieClip = new MovieClip();
		
		/* Define the Xml Loader variable */
		private var myLoader:URLLoader = new URLLoader();
		
		/*Define the main slider variables */
		private var slider_width:Number;
		private var slider_height:Number;
		
		/* Define the thumb variables */
		private var thumb_width:Number;
		private var thumb_height:Number;
		private var thumb_margin:Number;
		private var thumb_background_color:Number;
		private var thumb_background_alpha:Number;
		private var thumb_cover_color:Number;
		private var thumb_cover_alpha:Number;
		private var thumb_total_width:Number;
		private var thumb_step:Number;
		private var thumb_position:Boolean;
		
		/* Define the info variables */
		private var info_width:Number;
		private var info_height:Number;
		private var button_size:Number;
		private var button_height:Number;
		private var info_space:Number;
		private var next_picture:String;
		private var previous_picture:String;
		private var play_picture:String;
		private var pause_picture:String;
		
		/* Define the view variables */
		private var loader_size:Number;
		private var effect_time:Number;
		private var time_bar_height:Number;
		private var time_bar_alpha:Number;
		private var time_bar_color:Number;
		private var auto_play:Boolean;
		private var time:Number;
		private var freez_timer:Number;
		
		/* Define other variables that I will use for this component */
		private var active_picture:Number;
		private var loaderAphaTween:Tween;
		private var textMaskTween:Tween;
		private var timer:Number;;
		
		private var xmlFile:XML;
		
		private var thumbLoaderArray:Array = new Array();
		private var thumbItemsArray:Array = new Array();
		private var thumbCoverArray:Array = new Array();
		private var thumbHotspotArray:Array = new Array();
		private var bitAlphaArray:Array = new Array();
		private var coverAlphaArray:Array = new Array();
		private var viewItemArray:Array = new Array();
		private var moveTweenArray:Array = new Array();
		
		/* Main constructor */
		public function Main() {
			
			if (stage) 
				init();
			else 
				addEventListener(Event.ADDED_TO_STAGE, init);
				
		}
		
		/* Initialize the component */
		private function init(e:Event=null){
			
			/* Set the stage align and scale */
			stage.align     = StageAlign.TOP_LEFT;
			stage.scaleMode = StageScaleMode.NO_SCALE;
			
			/* Adding the movies to the stage */
			this.addChild(slider);
			
			slider.addChild(view);
			slider.addChild(info);
			slider.addChild(thumb);
			
			/* Loading the data xml file */
			var XMLDataPath:String = root.loaderInfo.parameters.XMLFile;
			if (XMLDataPath == null)
			{
				XMLDataPath = "xml/config.xml";
			}
			
			myLoader.load(new URLRequest(XMLDataPath));
			myLoader.addEventListener(Event.COMPLETE, processXML);
			
		}
		
		/* Processing the Xml File */
		private function processXML(e:Event){
			
			xmlFile = new XML(e.target.data);
			
			/* Geting the main variables from the Xml file */
			if ((xmlFile.width!="")&&(Number(xmlFile.width)!=0))
				slider_width = Number ( xmlFile.width );
			else
				slider_width = stage.stageWidth;
			
			if ((xmlFile.height!="")&&(Number(xmlFile.height)!=0))
				slider_height = Number ( xmlFile.height );
			else
				slider_height = stage.stageHeight;
				
			thumb_width = Number ( xmlFile.thumb.width );
			thumb_height = Number ( xmlFile.thumb.height );
			thumb_margin = Number ( xmlFile.thumb.margin );
			thumb_background_color = Number ( xmlFile.thumb.backgroundColor );
			thumb_background_alpha = Number ( xmlFile.thumb.backgroundAlpha );
			thumb_cover_color = Number ( xmlFile.thumb.coverColor );
			thumb_cover_alpha = Number ( xmlFile.thumb.coverAlpha );
			if (xmlFile.thumb.topPosition=="yes")
				thumb_position=true;
			else
				thumb_position=false;
			
			info_width = Number ( xmlFile.info.width );
			info_height = Number ( xmlFile.info.height );
			button_size = Number ( xmlFile.info.buttonSize );
			button_height = Number ( xmlFile.info.buttonHeights );
			info_space = Number ( xmlFile.info.space );
			next_picture = String ( xmlFile.info.nextButton );
			previous_picture = String ( xmlFile.info.previousButton );
			play_picture = String ( xmlFile.info.playButton );
			pause_picture = String ( xmlFile.info.pauseButton );
			
			loader_size = Number ( xmlFile.view.loaderSize );
			effect_time = Number ( xmlFile.view.effectTime );
			time_bar_color = Number ( xmlFile.view.timeBarColor );
			time_bar_alpha = Number ( xmlFile.view.timeBarAlpha );
			time_bar_height = Number ( xmlFile.view.timeBarHeight );
			time = Number ( xmlFile.view.timer );
			
			if ( xmlFile.view.autoPlay == "no" )
				auto_play=false;
			else
				auto_play=true;
			
			active_picture=0;
			
			/* Creating the info area */
			slider.visible=false;
			create_info();
			
		}
		
		/* Creating the view area */
		private function create_view(){
			
			if (thumb_position)
				view.y=slider_height-(slider_height-info_height-thumb_height-2*info_space);
			else
				view.y=0;
				
			/* Adding view items */
			view.addChild(viewItems);
			
			/* Adding view mask */
			var viewMask:MovieClip = new MovieClip();
				viewMask.graphics.beginFill(0x00FFFF,1);
				viewMask.graphics.drawRect(0,0,slider_width,slider_height-info_height-thumb_height-2*info_space);
				viewMask.graphics.endFill();
			view.addChild(viewMask);
			viewItems.mask = viewMask;
			
			/* Adding view Loader */
			viewLoader = new MainLoader();
			viewLoader.width=viewLoader.height=loader_size;
			view.addChild(viewLoader);
			
			viewLoader.x = slider_width/2;
			viewLoader.y = (slider_height-info_height-thumb_height-2*info_space)/2;
			
			/* Adding view timer bar */
			viewTimer.y=slider_height-info_height-thumb_height-2*info_space-time_bar_height;
			viewTimer.graphics.beginFill(time_bar_color,time_bar_alpha/100);
			viewTimer.graphics.drawRect(0,0,slider_width,time_bar_height);
			viewTimer.graphics.endFill();
			view.addChild(viewTimer);
			
			/* Adding view hotspot */
			var viewHotspot:MovieClip = new MovieClip();
				viewHotspot.graphics.beginFill(0x00FFFF,0);
				viewHotspot.graphics.drawRect(0,0,slider_width,slider_height-info_height-thumb_height-2*info_space);
				viewHotspot.graphics.endFill();
				viewHotspot.addEventListener(MouseEvent.MOUSE_OVER,freez_time);
				viewHotspot.addEventListener(MouseEvent.MOUSE_OUT,unfreez_time);
				viewHotspot.addEventListener(MouseEvent.CLICK,get_url);
			view.addChild(viewHotspot);
			
			freez_timer = -1;
			timer = -1;
			view.addEventListener(Event.ENTER_FRAME,check_timer);
			
			/* Adding first item to view area */
			show_view(0);
		}
		
		/* Freez timer on mouse over the view area */
		private function freez_time(e:Event){
			
			if (auto_play == true)
				freez_timer = getTimer();
		}
		
		/* Unfreez timer on mouse out the view area */
		private function unfreez_time(e:Event){
			
			if (freez_timer!=-1){
				if (auto_play == true)
					timer += (getTimer()-freez_timer);
				freez_timer = -1;
			}
			
		}
		
		/* Checking the timer status and changing the timer bar width */
		private function check_timer(e:Event){
			
			if (timer == -1)
				viewTimer.width=slider_width;
			else{
				if ((slider_width - ((slider_width/time)*(getTimer()-timer))>=0)&&(freez_timer==-1)){
					if (freez_timer==-1)
						viewTimer.width= slider_width - ((slider_width/time)*(getTimer()-timer));
				}
				else {
					if (freez_timer==-1){
						viewTimer.width=0;
						timer=-1;
						change_next_picture();
					}
				}
			}
		}
		
		/* Open url in case the user click on the view area */
		private function get_url(e:Event){
			
			if (xmlFile.items.*[active_picture].link!=""){
				if (auto_play == true)
					timer += (getTimer()-freez_timer);
				freez_timer = -1;
				navigateToURL(new URLRequest(String (xmlFile.items.*[active_picture].link)),String (xmlFile.items.*[active_picture].target));
			}
			
		}
		
		/* Create a view item */
		private function show_view(nr:Number){
			
			if (viewItemArray[nr]==null){
				viewLoader.alpha=1;
				viewTimer.visible=false;
				var viewItem:MovieClip = new MovieClip();
					viewItem.index=nr;
					viewItem.x=slider_width;
					
				var viewMask:MovieClip = new MovieClip();
					viewMask.graphics.beginFill(0x00FFFF,1);
					viewMask.graphics.drawRect(0,0,slider_width,slider_height-info_height-thumb_height-2*info_space);
					viewMask.graphics.endFill();
				viewItem.addChild(viewMask);
				
				var vLoader:Loader = new Loader();
					vLoader.load( new URLRequest(xmlFile.items.*[nr].view));
					vLoader.contentLoaderInfo.addEventListener(Event.COMPLETE,resize_view);
				vLoader.mask = viewMask;
				viewItem.addChild(vLoader);
				
				viewItems.addChild(viewItem);
				viewItemArray[nr]=viewItem;
			}
			else{
				viewTimer.visible=false;
				viewLoader.alpha=1;
				move_picture(nr);
			}

		}
		
		/* Create the move picture effect */
		private function move_picture(nr:Number){
			
			var moveTween:Tween;
			var old_active:Number;
			var coverAlphaTween:Tween;
			
			if (nr>active_picture){
				moveTween = new Tween(viewItemArray[active_picture], "x", Strong.easeOut, viewItemArray[active_picture].x, -slider_width, effect_time, true);
				moveTweenArray[active_picture]=moveTween;
			}
			else{
				moveTween = new Tween(viewItemArray[active_picture], "x", Strong.easeOut, viewItemArray[active_picture].x, slider_width, effect_time, true);
				moveTweenArray[active_picture]=moveTween;
			}
			
			coverAlphaTween = new Tween(thumbCoverArray[active_picture], "alpha", Strong.easeOut, thumbCoverArray[active_picture].alpha, thumb_cover_alpha/100, effect_time, true);
			coverAlphaArray[active_picture] = coverAlphaTween;
				
			old_active=active_picture;
			active_picture = nr;
			loaderAphaTween = new Tween(viewLoader, "alpha", Strong.easeOut, viewLoader.alpha, 0, effect_time, true);
			
			textMask.width=0;
			infoText.htmlText=xmlFile.items.*[active_picture].text;
			infoText.x=button_size+(info_width-button_size-infoText.width)/2;
			textMaskTween = new Tween(textMask, "width", Strong.easeOut, textMask.width, info_width-button_size, effect_time, true);
			coverAlphaTween= new Tween(thumbCoverArray[active_picture], "alpha", Strong.easeOut, thumbCoverArray[active_picture].alpha, 0, effect_time, true);
			coverAlphaArray[active_picture] = coverAlphaTween;
			
			if (active_picture>old_active){
				moveTween = new Tween(viewItemArray[active_picture], "x", Strong.easeOut, slider_width, 0, effect_time, true);
				moveTweenArray[active_picture]=moveTween;
			}
			else{
				moveTween = new Tween(viewItemArray[active_picture], "x", Strong.easeOut, -slider_width, 0, effect_time, true);
				moveTweenArray[active_picture]=moveTween;
			}
			
			viewTimer.width=slider_width;
			viewTimer.visible=true;
			if (auto_play == true){
				timer = getTimer();
			}
			
		}
		
		/* Resize the view items and create move effect*/
		private function resize_view(e:Event){
		
			var rationY:Number;
			var rationX:Number;
			var moveTween:Tween;
			var coverAlphaTween:Tween;
			var old_active:Number;
			
			var bit:Bitmap = e.target.content;
				bit.smoothing = true;
				
			rationY = bit.height / bit.width;
			rationX = bit.width / bit.height;
			
			if (((slider_height-info_height-thumb_height-2*info_space) / slider_width) < rationY) {
				bit.width = slider_width;
				bit.height = rationY * bit.width;
			} else {
				bit.height = slider_height-info_height-thumb_height-2*info_space;
				bit.width = rationX * bit.height;
			}
			
			bit.x=(slider_width-bit.width)/2;
			bit.y=((slider_height-info_height-thumb_height-2*info_space)-bit.height)/2;
			
			if (e.target.content.parent.parent.index>active_picture){
				moveTween = new Tween(viewItemArray[active_picture], "x", Strong.easeOut, viewItemArray[active_picture].x, -slider_width, effect_time, true);
				moveTweenArray[active_picture]=moveTween;
			}
			else{
				moveTween = new Tween(viewItemArray[active_picture], "x", Strong.easeOut, viewItemArray[active_picture].x, slider_width, effect_time, true);
				moveTweenArray[active_picture]=moveTween;
			}
			coverAlphaTween = new Tween(thumbCoverArray[active_picture], "alpha", Strong.easeOut, thumbCoverArray[active_picture].alpha, thumb_cover_alpha/100, effect_time, true);
			coverAlphaArray[active_picture] = coverAlphaTween;
			
			old_active=active_picture;
			active_picture = e.target.content.parent.parent.index;
			
			textMask.width=0;
			infoText.htmlText=xmlFile.items.*[active_picture].text;
			infoText.x=button_size+(info_width-button_size-infoText.width)/2;
			coverAlphaTween= new Tween(thumbCoverArray[active_picture], "alpha", Strong.easeOut, thumbCoverArray[active_picture].alpha, 0, effect_time, true);
			coverAlphaArray[active_picture] = coverAlphaTween;
			loaderAphaTween = new Tween(viewLoader, "alpha", Strong.easeOut, viewLoader.alpha, 0, effect_time, true);
			textMaskTween = new Tween(textMask, "width", Strong.easeOut, textMask.width, info_width-button_size, effect_time, true);
			
			if (active_picture>old_active){
				moveTween = new Tween(viewItemArray[active_picture], "x", Strong.easeOut, slider_width, 0, effect_time, true);
				moveTweenArray[active_picture]=moveTween;
			}
			else{
				moveTween = new Tween(viewItemArray[active_picture], "x", Strong.easeOut, -slider_width, 0, effect_time, true);
				moveTweenArray[active_picture]=moveTween;
			}
			
			viewTimer.width=slider_width;
			viewTimer.visible=true;
			if (auto_play == true){
				timer = getTimer();
			}
			
		}
		
		/* Create the thumbs area */
		private function create_thumb(){
			
			thumb.x=(slider_width-thumb_width)/2;
			
			if (thumb_position)
				thumb.y=0;
			else
				thumb.y=slider_height-thumb_height;
			
			var thumbBackground:MovieClip = new MovieClip();
				thumbBackground.graphics.beginFill(thumb_background_color,thumb_background_alpha/100);
				thumbBackground.graphics.drawRect(0,0,thumb_width,thumb_height);
				thumbBackground.graphics.endFill();
			thumb.addChild(thumbBackground);
			
			var thumbScroll:MovieClip = new MovieClip();
				thumbScroll.y=thumb_margin;
			thumb.addChild(thumbScroll);
			
			var thumbMask:MovieClip = new MovieClip();
				thumbMask.graphics.beginFill(0x00FFFF,1);
				thumbMask.graphics.drawRect(0,0,thumb_width,thumb_height-2*thumb_margin);
				thumbMask.graphics.endFill();
			thumbScroll.mask=thumbMask;
			thumbScroll.addChild(thumbMask);
			
			
			thumbScroll.addChild(thumbItems);
				
			thumb_total_width = thumb_margin;
			
			for (var i=0;i<xmlFile.items.*.length();i++){
				
				var item:MovieClip = new MovieClip();
					thumbItemsArray[i]=item;
					item.index=i;
					
				var itemLoader:Loader = new Loader();
					thumbLoaderArray[i]=itemLoader;
					item.addChild(itemLoader);
					
				var itemCover:MovieClip = new MovieClip();
					itemCover.visible=false;
					itemCover.graphics.beginFill(thumb_cover_color,1);
					itemCover.graphics.drawRect(0,0,thumb_height-2*thumb_margin,thumb_height-2*thumb_margin);
					itemCover.graphics.endFill();
					if (i!=active_picture)
						itemCover.alpha=thumb_cover_alpha/100;
					else
						itemCover.alpha=0;
					thumbCoverArray[i]=itemCover;
				item.addChild(itemCover);
				
					
				var itemHotspot:MovieClip = new MovieClip();
					itemHotspot.index=i;
					itemHotspot.graphics.beginFill(0x00FFFF,0);
					itemHotspot.graphics.drawRect(0,0,thumb_height-2*thumb_margin,thumb_height-2*thumb_margin);
					itemHotspot.graphics.endFill();
					itemHotspot.buttonMode = true;
					itemHotspot.useHandCursor = true;
					itemHotspot.addEventListener(MouseEvent.MOUSE_OVER,thumb_over);
					itemHotspot.addEventListener(MouseEvent.MOUSE_OUT,thumb_out);
					itemHotspot.addEventListener(MouseEvent.CLICK,change_picture);
					thumbHotspotArray[i]=itemHotspot;
					item.addChild(itemHotspot);
					
				thumbItems.addChild(item);
				
			}
			thumbLoaderArray[0].load(new URLRequest(xmlFile.items.*[0].thumb));
			thumbLoaderArray[0].contentLoaderInfo.addEventListener(Event.COMPLETE,resize_thumb);
			
			create_view();
			slider.visible=true;
			
		}
		
		/* Change the picture in case the thumb item is pressed */
		private function change_picture(e:Event){
			
			if (e.target.index!=active_picture){
				viewLoader.alpha=1;
				show_view(e.target.index);
			}
			
		}
		
		/* Create the thumb over effect */
		private function thumb_over(e:Event){
			
			if (e.target.index!=active_picture){
				var coverAlphaTween:Tween = new Tween(thumbCoverArray[e.target.index], "alpha", Strong.easeOut, thumbCoverArray[e.target.index].alpha, 0, effect_time, true);
				coverAlphaArray[e.target.index] = coverAlphaTween;
			}
		}
		
		/* Create the thumb out effect */
		private function thumb_out(e:Event){
			
			if (e.target.index!=active_picture){
				var coverAlphaTween:Tween = new Tween(thumbCoverArray[e.target.index], "alpha", Strong.easeOut, thumbCoverArray[e.target.index].alpha, thumb_cover_alpha/100, effect_time, true);
				coverAlphaArray[e.target.index] = coverAlphaTween;
			}
		}
		
		/* Resize the thumb picture and add it to the thumb scroll */
		private function resize_thumb(e:Event){
			
			var pic_number:Number;
			pic_number = e.target.content.parent.parent.index;
			
			thumbItemsArray[pic_number].x=thumb_total_width;
			
			var bit:Bitmap = e.target.content;
				bit.smoothing = true;
				bit.alpha=0;
			var bitAlphaTween:Tween = new Tween (bit, "alpha", Strong.easeOut, bit.alpha, 1, effect_time, true);
			bitAlphaArray[pic_number]=bitAlphaTween;
			
			bit.height = (thumb_height-2*thumb_margin);
			bit.scaleX=bit.scaleY;
			
			thumbCoverArray[pic_number].width = bit.width;
			thumbHotspotArray[pic_number].width = bit.width;
			thumbCoverArray[pic_number].visible = true;
			
			thumb_total_width += bit.width + thumb_margin;
			if ( thumb_total_width < thumb_width )
				thumbItems.x=(slider_width-thumb_total_width)/2;
			else{
				thumbItems.x=0;
				thumb_step=(thumb_total_width-thumb_width+thumb_height)/thumb_width;
				thumb.addEventListener(Event.ENTER_FRAME,check_scroll);
			}
			
			pic_number+=1;
			
			if (pic_number<xmlFile.items.*.length()){
				thumbLoaderArray[pic_number].load(new URLRequest(xmlFile.items.*[pic_number].thumb));
				thumbLoaderArray[pic_number].contentLoaderInfo.addEventListener(Event.COMPLETE,resize_thumb);
			}
				
				
		}
		
		/* Resize the info buttons */
		private function resize_exact(e:Event){
			var bit:Bitmap = e.target.content;
				bit.smoothing = true;
			
			bit.width=button_size;
			bit.height=button_height;
			
		}
		
		/* Check and set the thumb items scroll bar position */
		private function check_scroll(e:Event){
			
			var newX:Number;
			
			if ((thumb.mouseX>0)&&(thumb.mouseX<slider_width)&&(thumb.mouseY>0)&&(thumb.mouseY<thumb_height)){
				newX = -thumb_step*thumb.mouseX+thumb_height/2;
				if (newX>0)
					newX=0;
				if (newX<thumb_width-thumb_total_width)
					newX=thumb_width-thumb_total_width;
			}
			else{
				newX = -thumbItemsArray[active_picture].x+thumb_margin;
				if (newX<thumb_width-thumb_total_width)
					newX=thumb_width-thumb_total_width;
			}
			
			if (thumbItems.x>newX)
				thumbItems.x-=(thumbItems.x-newX)/5;
			if (thumbItems.x<newX)
				thumbItems.x+=(newX-thumbItems.x)/5;
				
		}
		
		/* Create the info area with all the info elements */
		private function create_info(){
			
			info.x=(slider_width-info_width-2*button_size)/2;
			
			if (thumb_position)
				info.y=thumb_height+info_space;
			else
				info.y=(slider_height-thumb_height-info_height-info_space);
			
			infoText.x=button_size;
			infoText.autoSize = "left";
			infoText.selectable = false;
			infoText.condenseWhite = true;
			infoText.multiline = true;
			infoText.htmlText = xmlFile.items.*[active_picture].text;
			info.addChild(infoText);
			
			infoText.y=(info_height-infoText.height)/2;
			infoText.x=(info_width-button_size-infoText.width)/2;
			
			textMask.x=button_size;
			textMask.graphics.beginFill(0x00FFFF,1);
			textMask.graphics.drawRect(0,0,info_width-button_size,info_height);
			textMask.graphics.endFill();
			textMask.width=0;
			infoText.mask = textMask;
			info.addChild(textMask);
			
			var nextButton:MovieClip = new MovieClip();
				//nextButton.y=(info_height-button_size)/2;
				nextButton.y=131;
				nextButton.height=button_height;
				//nextButton.x=info_width;
				nextButton.x=321;
				nextButton.width=button_size;
			info.addChild(nextButton);
			
			
			nextGraphic.load( new URLRequest(next_picture));
			nextGraphic.contentLoaderInfo.addEventListener(Event.COMPLETE,resize_exact);
			nextButton.addChild(nextGraphic);
			
			var nextHotspot:MovieClip = new MovieClip();
				nextHotspot.graphics.beginFill(0x00FFFF,0);
				nextHotspot.graphics.drawRect(0,0,button_size,button_height);
				nextHotspot.graphics.endFill();
				nextHotspot.buttonMode=true;
				nextHotspot.useHandCursor=true;
				nextHotspot.addEventListener(MouseEvent.CLICK,change_next_picture);
			nextButton.addChild(nextHotspot);
				
			var previousButton:MovieClip = new MovieClip();
				previousButton.y=(info_height-button_size)+20/*/2*/;
				previousButton.x=191;
			info.addChild(previousButton);
			
			
			previousGraphic.load( new URLRequest(previous_picture));
			previousGraphic.contentLoaderInfo.addEventListener(Event.COMPLETE,resize_exact);
			previousButton.addChild(previousGraphic);
			
			var previousHotspot:MovieClip = new MovieClip();
				previousHotspot.graphics.beginFill(0x00FFFF,0);
				previousHotspot.graphics.drawRect(0,0,button_size,button_height);
				previousHotspot.graphics.endFill();
				previousHotspot.buttonMode=true;
				previousHotspot.useHandCursor=true;
				previousHotspot.addEventListener(MouseEvent.CLICK,change_previous_picture);
			previousButton.addChild(previousHotspot);
			
			
			var playpauseButton:MovieClip = new MovieClip();
				playpauseButton.x=0;/*info_width+button_size/*+((slider_width-info_width)/2-button_size)/2;*/
				playpauseButton.y=(info_height-button_size)+20;/*2*;*/
//				playpauseButton.alpha=0;
			
			if (auto_play)
				playGraphic.visible=false;
			playGraphic.load( new URLRequest(play_picture));
			playGraphic.contentLoaderInfo.addEventListener(Event.COMPLETE,resize_exact);
			playpauseButton.addChild(playGraphic);
			
			if (auto_play==false)
				pauseGraphic.visible=false;
			pauseGraphic.load( new URLRequest(pause_picture));
			pauseGraphic.contentLoaderInfo.addEventListener(Event.COMPLETE,resize_exact);
			playpauseButton.addChild(pauseGraphic);
				
			var playpauseHotspot:MovieClip = new MovieClip();
				playpauseHotspot.graphics.beginFill(0x00FFFF,0);
				playpauseHotspot.graphics.drawRect(0,0,button_size,button_height);
				playpauseHotspot.graphics.endFill();
				playpauseHotspot.buttonMode=true;
				playpauseHotspot.useHandCursor=true;
//				playpauseHotspot.addEventListener(MouseEvent.CLICK,change_autoplay);
				playpauseHotspot.addEventListener(MouseEvent.CLICK,get_url);
			playpauseButton.addChild(playpauseHotspot);
			
			info.addChild(playpauseButton);
			slider.addEventListener(Event.ENTER_FRAME,check_content);
		}
		
		/* Change the picture with the next one */
		private function change_next_picture(e:Event=null){
			
			var next_pic:Number;
			next_pic = active_picture+1;
			if (next_pic>=xmlFile.items.*.length())
				next_pic=0;
			show_view(next_pic);
			
		}
		
		/* Change the picture with the previous one */
		private function change_previous_picture(e:Event){
			
			var previous_pic:Number;
			previous_pic = active_picture-1;
			if (previous_pic<0)
				previous_pic=xmlFile.items.*.length()-1;
			show_view(previous_pic);
			
		}
		
		/* Change the auto play graphic and status to true and false */
		private function change_autoplay(e:Event){
			
			if (playGraphic.visible==true){
				playGraphic.visible=false;
				pauseGraphic.visible=true;
				auto_play=true;
				timer=getTimer();
			}
			else{
				pauseGraphic.visible=false;
				playGraphic.visible=true;
				auto_play=false;
				timer=-1;
			}
			
		}
		
		/* Check the content bytes loaded before I display the component */
		private function check_content(e:Event){
			
			var bytes_total:Number;
			var bytes_loaded:Number;
			var load_ok:Boolean;
			
			load_ok = true;
			bytes_total = 0;
			bytes_loaded = 0;
			
			if (playGraphic.contentLoaderInfo.bytesTotal>0){
				bytes_total += playGraphic.contentLoaderInfo.bytesTotal;
				bytes_loaded += playGraphic.contentLoaderInfo.bytesLoaded;
			}
			else
				load_ok = false;
				
			if (pauseGraphic.contentLoaderInfo.bytesTotal>0){
				bytes_total += pauseGraphic.contentLoaderInfo.bytesTotal;
				bytes_loaded += pauseGraphic.contentLoaderInfo.bytesLoaded;
			}
			else
				load_ok = false;
				
			if (nextGraphic.contentLoaderInfo.bytesTotal>0){
				bytes_total += nextGraphic.contentLoaderInfo.bytesTotal;
				bytes_loaded += nextGraphic.contentLoaderInfo.bytesLoaded;
			}
			else
				load_ok = false;
				
			if (previousGraphic.contentLoaderInfo.bytesTotal>0){
				bytes_total += previousGraphic.contentLoaderInfo.bytesTotal;
				bytes_loaded += previousGraphic.contentLoaderInfo.bytesLoaded;
			}
			else
				load_ok = false;
				
			if ((load_ok)&&(bytes_loaded == bytes_total)){
				create_thumb();
				slider.removeEventListener(Event.ENTER_FRAME,check_content);
			}
				
		}
		
	}
	
}