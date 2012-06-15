package com.aigrenis.milka {
    import flash.geom.Rectangle;
    import flash.geom.Point;
    
    public class Espiral{
        public static var homePoint:Point = new Point(0,0);
        public function Espiral(){
        }
        
    
        public static function calculateItemPosition(distance:Number,position:int,stage):Point{
            homePoint.x = Math.floor((stage.stageWidth)/2);
            homePoint.y = Math.floor((stage.stageHeight)/2);
			//homePoint.x = 0;
			//trace(stage.stageWidth+":"+homePoint.x+ " - " + stage.stageHeight+":"+homePoint.y);
            var k:int;
            var e:int;
            var top:int;
            var limit:int=position+1;
            var count:int=0;
            var until:int=0;
            //trace("\nlimit = "+limit);
            for(top=1;top<limit+1;top++){
                for(k=0;k<2;k++){
                    for(e=0;e<top;e++){
                        defineDirection(count,distance);
                        until++;
                        if(until==limit){
                            break;
                        }
                    }
                    count++;
                    if(until==limit){
                        break;
                    }
                }
                if(until==limit){
                    break;
                }
            }
            //trace("devolviendo:"+homePoint.x +":"+homePoint.y);
            return homePoint;
        }
        

        private static function defineDirection(inn:int,distance:Number=1):Point{
            var n:int = inn%4;
            if(!n){
                homePoint.x+=1*distance;
            }else if(n==1){
                homePoint.y+=1*distance;
            }else if(n==2){
                homePoint.x-=1*distance;
            }else{
                homePoint.y-=1*distance;
            }
			//trace (homePoint.x);
            return homePoint;
        }
    }
}