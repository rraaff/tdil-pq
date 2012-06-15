package com.aigrenis.milka {
	import flash.display.Sprite;
	import flash.display.Stage;
	import fl.transitions.Tween;
	import fl.transitions.easing.*;
	import flash.events.MouseEvent;
	import flash.events.Event;
	import flash.display.MovieClip;
	public class Shader extends MovieClip {
		public var square:Sprite;
		private var stagew:Number;
		private var stageh:Number;
		private var opacidad:Number;

		public function generate(valopacidad:Number) {
			square = new Sprite();
			this.setOpacidad(valopacidad);
			addChild(square);
			square.name = "shader";
			//getChildAt(0)
			square.addEventListener(MouseEvent.CLICK, closeShader, false, 0, true);
			square.graphics.lineStyle(0,0x000000);
			square.graphics.beginFill(0x000000);
			square.graphics.drawRect(0,0,stage.stageWidth,stage.stageHeight);
			square.graphics.endFill();
			square.x = 0;
			square.y = 0;
			square.alpha=0;
			var myTween:Tween = new Tween(square, "alpha", Strong.easeOut, 0, this.opacidad, 2, true);
		}
		public function setOpacidad(valopacidad:Number):void {
			this.opacidad = valopacidad*0.01;
		}
		private function closeShader(event:MouseEvent):void {
			Global.closeGranpostit=true;
			removeEventListener(MouseEvent.CLICK, closeShader);
			var myTween:Tween = new Tween(square, "alpha", Strong.easeOut, this.opacidad, 0, 1, true);
			this.addEventListener(Event.ENTER_FRAME, destroyShader, false, 0 ,false);
			//var toBeRemove = getChildByName("shader");
			//toBeRemove.parent.removeChild(toBeRemove);

		}
		private function destroyShader(event:Event):void {
			if (square.alpha<0.1) {
				removeEventListener(Event.ENTER_FRAME, destroyShader);
				var toBeRemove = getChildByName("shader");
				toBeRemove.parent.removeChild(toBeRemove);
			}
		}
	}
}