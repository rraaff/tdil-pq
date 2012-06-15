package com.aigrenis.milka {
	import flash.display.MovieClip;
	import flash.display.SimpleButton;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.display.Loader;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;

	public class Boton extends MovieClip {
		private var my_button:SimpleButton;
		public var posx:int;
		public var posy:int;
		public var urlUp:String;
		public var urlOver:String;
		public var urlHit:String;
		public var urlboton:String;

		public function Boton(posx:int,posy:int,urlUp:String,urlOver:String,urlHit:String,urlboton:String) {
			this.posx = posx;
			this.posy = posy;
			this.urlUp = urlUp;
			this.urlOver = urlOver;
			this.urlHit = urlHit;
			this.urlboton = urlboton;
			init();
		}
		private function init():void {
			//stage.frameRate=31;
			creaBoton();
			addChild(my_button);
			agregarListener();
		}
		private function creaBoton():void {
			my_button=new SimpleButton();
			my_button.x=this.posx;
			my_button.y=this.posy;

			my_button.upState=cargarImagen(this.urlUp);
			my_button.overState=cargarImagen(this.urlOver);
			my_button.downState=cargarImagen(this.urlHit);
			my_button.hitTestState=my_button.upState;
		}
		private function cargarImagen(url:String):Sprite {
			var cont:Sprite=new Sprite();
			var fondo = new Loader();
			cont.addChild(fondo);
			//trace(url);
			fondo.load(new URLRequest(url));
			return cont;
		}
		private function agregarListener():void {
			my_button.addEventListener(MouseEvent.CLICK,clickme);
			function clickme(m:MouseEvent):void {
				var request:URLRequest = new URLRequest(urlboton);
				navigateToURL(request, '_self');
			}
		}
	}
}