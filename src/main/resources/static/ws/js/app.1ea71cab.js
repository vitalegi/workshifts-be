(function(t){function e(e){for(var r,o,s=e[0],u=e[1],c=e[2],f=0,h=[];f<s.length;f++)o=s[f],Object.prototype.hasOwnProperty.call(a,o)&&a[o]&&h.push(a[o][0]),a[o]=0;for(r in u)Object.prototype.hasOwnProperty.call(u,r)&&(t[r]=u[r]);l&&l(e);while(h.length)h.shift()();return i.push.apply(i,c||[]),n()}function n(){for(var t,e=0;e<i.length;e++){for(var n=i[e],r=!0,s=1;s<n.length;s++){var u=n[s];0!==a[u]&&(r=!1)}r&&(i.splice(e--,1),t=o(o.s=n[0]))}return t}var r={},a={app:0},i=[];function o(e){if(r[e])return r[e].exports;var n=r[e]={i:e,l:!1,exports:{}};return t[e].call(n.exports,n,n.exports,o),n.l=!0,n.exports}o.m=t,o.c=r,o.d=function(t,e,n){o.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:n})},o.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},o.t=function(t,e){if(1&e&&(t=o(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var n=Object.create(null);if(o.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var r in t)o.d(n,r,function(e){return t[e]}.bind(null,r));return n},o.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return o.d(e,"a",e),e},o.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},o.p="";var s=window["webpackJsonp"]=window["webpackJsonp"]||[],u=s.push.bind(s);s.push=e,s=s.slice();for(var c=0;c<s.length;c++)e(s[c]);var l=u;i.push([0,"chunk-vendors"]),n()})({0:function(t,e,n){t.exports=n("cd49")},"05c0":function(t,e,n){"use strict";var r=n("077b"),a=n.n(r);a.a},"077b":function(t,e,n){},"5c0b":function(t,e,n){"use strict";var r=n("7694"),a=n.n(r);a.a},"5c0c":function(t,e,n){},"6a49":function(t,e,n){"use strict";var r=n("5c0c"),a=n.n(r);a.a},7694:function(t,e,n){},cd49:function(t,e,n){"use strict";n.r(e);n("e260"),n("e6cf"),n("cca6"),n("a79d"),n("db4d");var r=n("2b0e"),a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-app",[n("v-app-bar",{attrs:{app:"",color:"primary",dark:""}},[n("div",{staticClass:"d-flex align-center"},[n("v-img",{staticClass:"shrink mr-2",attrs:{alt:"Vuetify Logo",contain:"",src:"https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png",transition:"scale-transition",width:"40"}}),n("v-img",{staticClass:"shrink mt-1 hidden-sm-and-down",attrs:{alt:"Vuetify Name",contain:"","min-width":"100",src:"https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png",width:"100"}})],1),n("v-spacer"),n("v-btn",{attrs:{href:"https://github.com/vuetifyjs/vuetify/releases/latest",target:"_blank",text:""}},[n("span",{staticClass:"mr-2"},[t._v("Latest Release")]),n("v-icon",[t._v("mdi-open-in-new")])],1)],1),n("v-content",[n("WorkShiftTable",{attrs:{context:t.context,availableStates:["---","M","P","L104"]}})],1)],1)},i=[],o=(n("4160"),n("b0c0"),n("159b"),n("d4ec")),s=n("bee2"),u=n("262e"),c=n("2caf"),l=n("9ab4"),f=n("60a3"),h=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-container",{attrs:{fluid:""}},[n("v-row",[n("v-col",[n("div",{staticClass:"hello"},[n("h1",[t._v("Turni Lavorativi")])])])],1),n("v-row",[n("v-col",[n("MonthPicker",{attrs:{initialValue:t.context.from},on:{"update-date":t.handleUpdateFromDate}})],1)],1),n("v-row",[n("v-simple-table",{staticClass:"single-line",attrs:{dense:""},scopedSlots:t._u([{key:"default",fn:function(){return[n("thead",[n("tr",[n("th",{staticClass:"text-left start-of-group"},[t._v("Name")]),n("th",{staticClass:"text-left start-of-group"},[t._v("Group")]),n("th",{staticClass:"text-left start-of-group end-of-week"},[t._v("Subgroup")]),t._l(t.days(),(function(e){return n("th",{key:e,class:{"end-of-week":t.isEndOfWeek(t.day(e)),"table-header":!0,"text-center":!0,"start-of-group":!0}},[t._v(t._s(t.formatHeaderDate(t.day(e))))])}))],2),n("tr",[n("th",{staticClass:"text-left"}),n("th",{staticClass:"text-left"}),n("th",{staticClass:"text-left end-of-week"}),t._l(t.days(),(function(e){return n("th",{key:e,class:{"end-of-week":t.isEndOfWeek(t.day(e)),"table-header":!0,"text-center":!0}},[t._v(t._s(t.getDayOfWeekLabel(t.day(e))))])}))],2)]),n("tbody",[t._l(t.employees(),(function(e){return n("tr",{key:e},[n("td",[t._v(t._s(e)+" - "+t._s(t.context.getEmployee(e).name))]),n("td",[t._v(t._s(t.getGroupName(e)))]),n("td",{staticClass:"end-of-week"},[t._v(t._s(t.getSubgroupName(e)))]),t._l(t.days(),(function(r){return n("td",{key:r,class:{"end-of-week":t.isEndOfWeek(t.day(r)),"start-of-group":t.isStartOfGroup(e),"start-of-subgroup":t.isStartOfSubgroup(e),"full-space":!0}},[t.hasEmployeeErrors(e,t.day(r))?n("v-tooltip",{attrs:{top:""},scopedSlots:t._u([{key:"activator",fn:function(a){var i=a.on;return[n("span",t._g({},i),[n("WorkShiftInput",{attrs:{initialValue:t.getShift(e,t.day(r)),items:t.availableStates,hasErrors:!0},on:{"update-value":function(n){return t.handleShift(e,t.day(r),n)}}})],1)]}}],null,!0)},[n("span",t._l(t.getEmployeeErrors(e,t.day(r)),(function(e){return n("div",{key:e},[t._v(t._s(e))])})),0)]):n("WorkShiftInput",{attrs:{initialValue:t.getShift(e,t.day(r)),items:t.availableStates,hasErrors:!1},on:{"update-value":function(n){return t.handleShift(e,t.day(r),n)}}})],1)}))],2)})),n("tr",[n("td",{staticClass:"text-left",attrs:{rowspan:"4"}},[t._v("Cars")]),n("td",{staticClass:"text-left",attrs:{rowspan:"2"}},[t._v("Mattina")]),n("td",{staticClass:"text-left end-of-week"},[t._v("Usate")]),t._l(t.days(),(function(e){return n("td",{key:e,class:{"end-of-week":t.isEndOfWeek(t.day(e)),"table-header":!0,"text-center":!0,errors:t.hasCarsErrors(t.morningAction(),t.day(e))}},[t.hasCarsErrors(t.morningAction(),t.day(e))?n("v-tooltip",{attrs:{top:""},scopedSlots:t._u([{key:"activator",fn:function(r){var a=r.on;return[n("span",t._g({},a),[t._v(" "+t._s(t.getUsedCars(t.day(e),t.morningAction()))+" ")])]}}],null,!0)},[n("span",t._l(t.getCarsErrors(t.morningAction(),t.day(e)),(function(e){return n("div",{key:e},[t._v(t._s(e))])})),0)]):n("span",[t._v(" "+t._s(t.getUsedCars(t.day(e),t.morningAction()))+" ")])],1)}))],2),n("tr",[n("td",{staticClass:"text-left end-of-week"},[t._v("Disponibili")]),t._l(t.days(),(function(e){return n("td",{key:e,class:{"end-of-week":t.isEndOfWeek(t.day(e)),"table-header":!0,"text-center":!0}},[t._v(t._s(t.getTotCars()))])}))],2),n("tr",[n("td",{staticClass:"text-left",attrs:{rowspan:"2"}},[t._v("Pomeriggio")]),n("td",{staticClass:"text-left end-of-week"},[t._v("Usate")]),t._l(t.days(),(function(e){return n("td",{key:e,class:{"end-of-week":t.isEndOfWeek(t.day(e)),"table-header":!0,"text-center":!0,errors:t.hasCarsErrors(t.afternoonAction(),t.day(e))}},[t.hasCarsErrors(t.afternoonAction(),t.day(e))?n("v-tooltip",{attrs:{top:""},scopedSlots:t._u([{key:"activator",fn:function(r){var a=r.on;return[n("span",t._g({},a),[t._v(" "+t._s(t.getUsedCars(t.day(e),t.afternoonAction()))+" ")])]}}],null,!0)},[n("span",t._l(t.getCarsErrors(t.afternoonAction(),t.day(e)),(function(e){return n("div",{key:e},[t._v(t._s(e))])})),0)]):n("span",[t._v(" "+t._s(t.getUsedCars(t.day(e),t.afternoonAction()))+" ")])],1)}))],2),n("tr",[n("td",{staticClass:"text-left end-of-week"},[t._v("Disponibili")]),t._l(t.days(),(function(e){return n("td",{key:e,class:{"end-of-week":t.isEndOfWeek(t.day(e)),"table-header":!0,"text-center":!0}},[t._v(t._s(t.getTotCars()))])}))],2)],2)]},proxy:!0}])})],1)],1)},g=[],v=(n("99af"),n("c740"),function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{class:{"text-center":!0,errors:t.hasErrors}},[n("v-menu",{attrs:{"offset-y":""},scopedSlots:t._u([{key:"activator",fn:function(e){var r=e.on;return[n("span",t._g({},r),[t._v(t._s(t.value))])]}}])},[n("v-list",t._l(t.items,(function(e,r){return n("v-list-item",{key:r,on:{click:function(n){return t.select(e)}}},[n("v-list-item-title",[t._v(t._s(e))])],1)})),1)],1)],1)}),p=[],d=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(){var t;return Object(o["a"])(this,n),t=e.apply(this,arguments),t.currentValue="",t}return Object(s["a"])(n,[{key:"select",value:function(t){this.value=t,this.$emit("update-value",this.value)}},{key:"value",get:function(){return""==this.currentValue?this.initialValue:this.currentValue},set:function(t){this.currentValue=t}}]),n}(f["c"]);Object(l["a"])([Object(f["b"])()],d.prototype,"items",void 0),Object(l["a"])([Object(f["b"])()],d.prototype,"initialValue",void 0),Object(l["a"])([Object(f["b"])()],d.prototype,"hasErrors",void 0),d=Object(l["a"])([f["a"]],d);var y=d,b=y,k=(n("6a49"),n("2877")),m=n("6544"),O=n.n(m),S=n("8860"),_=n("da13"),w=n("5d23"),E=n("e449"),A=Object(k["a"])(b,v,p,!1,null,"7eb36052",null),D=A.exports;O()(A,{VList:S["a"],VListItem:_["a"],VListItemTitle:w["a"],VMenu:E["a"]});var j=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-row",{attrs:{justify:"center"}},[n("v-menu",{attrs:{"close-on-content-click":!1,"nudge-right":40,transition:"scale-transition","offset-y":"","min-width":"290px"},scopedSlots:t._u([{key:"activator",fn:function(e){var r=e.on;return[n("v-text-field",t._g({attrs:{label:"Picker without buttons",readonly:""},model:{value:t.currentValue,callback:function(e){t.currentValue=e},expression:"currentValue"}},r))]}}]),model:{value:t.menu,callback:function(e){t.menu=e},expression:"menu"}},[n("v-date-picker",{attrs:{type:"month"},on:{input:function(e){t.menu=!1},change:t.update},model:{value:t.currentValue,callback:function(e){t.currentValue=e},expression:"currentValue"}})],1)],1)},I=[],N=(n("4d63"),n("ac1f"),n("25f0"),n("b812")),C=(new N["LoggerFactoryOptions"]).addLogGroupRule(new N["LogGroupRule"](new RegExp("services.+"),N["LogLevel"].Info)).addLogGroupRule(new N["LogGroupRule"](new RegExp("utils.Decorators.StatsCollector"),N["LogLevel"].Error)).addLogGroupRule(new N["LogGroupRule"](new RegExp(".+"),N["LogLevel"].Info)),x=N["LFService"].createNamedLoggerFactory("LoggerFactory",C),M=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(){var t;return Object(o["a"])(this,n),t=e.apply(this,arguments),t.logger=x.getLogger("components.MonthPicker"),t.currentValue="",t.menu=!1,t}return Object(s["a"])(n,[{key:"update",value:function(){var t=this;this.logger.info((function(){return"Updated month: ".concat(t.currentValue)})),this.$emit("update-date",this.currentValue+"-01")}},{key:"model",get:function(){return""==this.currentValue&&(this.currentValue=this.value.toUTCString().substring(0,10)),this.currentValue},set:function(t){this.currentValue=t}}]),n}(f["c"]);Object(l["a"])([Object(f["b"])()],M.prototype,"value",void 0),M=Object(l["a"])([f["a"]],M);var R=M,L=R,T=n("2e4b"),W=n("0fd9"),V=n("8654"),G=Object(k["a"])(L,j,I,!1,null,"6f61d4d4",null),Y=G.exports;O()(G,{VDatePicker:T["a"],VMenu:E["a"],VRow:W["a"],VTextField:V["a"]});var F;n("c975");(function(t){t[t["IDLE"]=0]="IDLE",t[t["MORNING"]=1]="MORNING",t[t["AFTERNOON"]=2]="AFTERNOON",t[t["AWAY"]=3]="AWAY"})(F||(F={}));var U,P=function(){function t(){Object(o["a"])(this,t)}return Object(s["a"])(t,[{key:"getAllLabels",value:function(){var t=[];return this.getLabels(F.IDLE).forEach((function(e){return t.push(e)})),this.getLabels(F.MORNING).forEach((function(e){return t.push(e)})),this.getLabels(F.AFTERNOON).forEach((function(e){return t.push(e)})),this.getLabels(F.AWAY).forEach((function(e){return t.push(e)})),t}},{key:"getLabels",value:function(t){return t==F.MORNING?["M","M*"]:t==F.AFTERNOON?["P","P*"]:t==F.AWAY?["Fer","L104","Rec"]:t==F.IDLE?[this.getDefaultLabel()]:[]}},{key:"getDefaultLabel",value:function(){return"---"}},{key:"getAction",value:function(t){if(-1!=this.getLabels(F.IDLE).indexOf(t))return F.IDLE;if(-1!=this.getLabels(F.MORNING).indexOf(t))return F.MORNING;if(-1!=this.getLabels(F.AFTERNOON).indexOf(t))return F.AFTERNOON;if(-1!=this.getLabels(F.AWAY).indexOf(t))return F.AWAY;throw new Error("Label ["+t+"] not recognized as an action")}}]),t}();n("1276");(function(t){t[t["MONDAY"]=1]="MONDAY",t[t["TUESDAY"]=2]="TUESDAY",t[t["WEDNESDAY"]=3]="WEDNESDAY",t[t["THURSDAY"]=4]="THURSDAY",t[t["FRIDAY"]=5]="FRIDAY",t[t["SATURDAY"]=6]="SATURDAY",t[t["SUNDAY"]=0]="SUNDAY"})(U||(U={}));var B=function(){function t(){Object(o["a"])(this,t),this.logger=x.getLogger("services.DateService")}return Object(s["a"])(t,[{key:"isStartOfWeek",value:function(t){return 1==t.getDay()}},{key:"isEndOfWeek",value:function(t){return 0==t.getDay()}},{key:"getStartOfMonth",value:function(t){return this.createDate(t.getFullYear(),t.getMonth(),1)}},{key:"getEndOfMonth",value:function(t){return this.createDate(t.getFullYear(),t.getMonth()+1,0)}},{key:"getStartOfWeek",value:function(t){var e=t;while(!this.isStartOfWeek(e))e=this.addDays(e,-1);return e}},{key:"getEndOfWeek",value:function(t){var e=t;while(!this.isEndOfWeek(e))e=this.addDays(e,1);return e}},{key:"getDayOfWeek",value:function(t){switch(t.getDay()){case 1:return U.MONDAY;case 2:return U.TUESDAY;case 3:return U.WEDNESDAY;case 4:return U.THURSDAY;case 5:return U.FRIDAY;case 6:return U.SATURDAY;case 0:return U.SUNDAY;default:throw new Error("Unrecognized date "+t)}}},{key:"getDayOfWeekLabel",value:function(t){switch(this.getDayOfWeek(t)){case U.MONDAY:return"L";case U.TUESDAY:return"M";case U.WEDNESDAY:return"M";case U.THURSDAY:return"G";case U.FRIDAY:return"V";case U.SATURDAY:return"S";case U.SUNDAY:return"D"}}},{key:"parse",value:function(t){var e=0,n=0,r=1;try{var a=t.split("-");e=parseInt(a[0]),n=parseInt(a[1])-1,3==a.length&&(r=parseInt(a[2]))}catch(i){throw Error("Text "+t+" is not a valid date."+i)}return this.createDate(e,n,r)}},{key:"format",value:function(t){var e=t.getFullYear(),n=t.getMonth()+1,r=t.getDate(),a=e+"-";return n<10&&(a+="0"),a+=n+"-",r<10&&(a+="0"),a+=r,a}},{key:"formatShort",value:function(t){var e=t.getMonth()+1,n=t.getDate(),r="";return n<10&&(r+="0"),r+=n+"-",e<10&&(r+="0"),r+=e,r}},{key:"isValidDate",value:function(t){return t instanceof Date&&!isNaN(t.getTime())}},{key:"range",value:function(t,e){var n=Array(),r=this.clone(t);while(!this.isAfter(r,e))n.push(r),r=this.addDays(r,1);return n}},{key:"isBefore",value:function(t,e){return t<e}},{key:"isAfter",value:function(t,e){return t>e}},{key:"createDate",value:function(t,e,n){var r=new Date(t,e,n);if(!this.isValidDate(r))throw Error("Not a valid date. ["+t+"] ["+e+"] ["+n+"]");return r.setHours(0,0,0,0),r}},{key:"getWeek",value:function(t){var e=this;this.logger.debug((function(){return"Input: ".concat(e.format(t))}));var n=this.getStartOfWeek(t),r=this.getEndOfWeek(t);return this.logger.debug((function(){return"Output: ".concat(e.format(n)," - ").concat(e.format(r))})),this.range(n,r)}},{key:"clone",value:function(t){return new Date(t.getTime())}},{key:"addDays",value:function(t,e){var n=this.clone(t);return n.setDate(n.getDate()+e),n}}]),t}(),z=(n("4de4"),n("5db7"),n("d81d"),n("73d9"),function(){function t(){Object(o["a"])(this,t),this.logger=x.getLogger("services.WorkShiftService")}return Object(s["a"])(t,[{key:"countByEmployeesDatesAction",value:function(t,e,n,r){return t.flatMap((function(t){var a=r.getEmployee(t);return e.map((function(t){return r.workShift.getAction(a,t)})).filter((function(t){return t==n}))})).length}}]),t}()),H=(n("a630"),n("d3b7"),n("3ca3"),n("ddb0"),function(){function t(){Object(o["a"])(this,t),this.dateService=tt.getInstance().getDateService()}return Object(s["a"])(t,[{key:"isValid",value:function(t,e,n){return 0==this.errors(t,e,n).length}},{key:"hasErrors",value:function(t,e,n){return!this.isValid(t,e,n)}}]),t}()),Z=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(){var t;return Object(o["a"])(this,n),t=e.apply(this,arguments),t.logger=x.getLogger("services.TotalShiftsPerWeek"),t}return Object(s["a"])(n,[{key:"errors",value:function(t,e,n){var r=new Array;if(null==t)return r;var a=tt.getInstance().getWorkShiftService(),i=this.dateService.getWeek(e),o=a.countByEmployeesDatesAction([t],i,F.MORNING,n)+a.countByEmployeesDatesAction([t],i,F.AFTERNOON,n)+a.countByEmployeesDatesAction([t],i,F.AWAY,n),s=n.getEmployee(t);return o!=s.totWeekShifts&&r.push("".concat(s.name," dovrebbe lavorare ").concat(s.totWeekShifts," turni in una settimana, presenti ").concat(o)),r}}]),n}(H),$=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(){var t;return Object(o["a"])(this,n),t=e.apply(this,arguments),t.logger=x.getLogger("services.MinShiftPerWeek"),t}return Object(s["a"])(n,[{key:"errors",value:function(t,e,n){var r=new Array;if(null==t)return r;var a=tt.getInstance().getWorkShiftService(),i=this.dateService.getWeek(e),o=n.getEmployee(t),s=o.maxWeekMornings,u=a.countByEmployeesDatesAction([t],i,F.MORNING,n),c=o.maxWeekAfternoons,l=a.countByEmployeesDatesAction([t],i,F.AFTERNOON,n);return s<u&&r.push("".concat(o.name," dovrebbe lavorare al massimo ").concat(s," mattine la settimana, presenti ").concat(u)),c<l&&r.push("".concat(o.name," dovrebbe lavorare al massimo ").concat(c," pomeriggi la settimana, presenti ").concat(l)),r}}]),n}(H),J=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(t){var r;return Object(o["a"])(this,n),r=e.call(this),r.logger=x.getLogger("services.MaxCarsPerShift"),r.workShiftService=tt.getInstance().getWorkShiftService(),r.action=t,r}return Object(s["a"])(n,[{key:"errors",value:function(t,e,n){var r=new Array,a=Array.from(n.employees.values()).map((function(t){return t.id})),i=this.workShiftService.countByEmployeesDatesAction(a,[e],this.action,n);return i>n.availableCars.total&&(this.action==F.MORNING&&r.push("Disponibili ".concat(n.availableCars.total," auto al mattino, assegnate ").concat(i,".")),this.action==F.AFTERNOON&&r.push("Disponibili ".concat(n.availableCars.total," auto al pomeriggio, assegnate ").concat(i,"."))),r}}]),n}(H),K=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(){var t;return Object(o["a"])(this,n),t=e.apply(this,arguments),t.logger=x.getLogger("services.AbstractMinShiftsPerGroup"),t.workShiftService=tt.getInstance().getWorkShiftService(),t}return Object(s["a"])(n,[{key:"errors",value:function(t,e,n){var r=this,a=new Array;if(null==t)return a;var i=n.getEmployee(t),o=this.getGroup(i,n),s=this.dateService.getDayOfWeek(e);if("undefined"===typeof o)return a;var u=Array.from(n.employees.values()).filter((function(t){var e;return(null===(e=r.getGroup(i,n))||void 0===e?void 0:e.id)==o.id})).map((function(t){return t.id})),c=o.constraints.getMin(s,F.MORNING),l=this.workShiftService.countByEmployeesDatesAction(u,[e],F.MORNING,n),f=o.constraints.getMin(s,F.AFTERNOON),h=this.workShiftService.countByEmployeesDatesAction(u,[e],F.AFTERNOON,n),g=n.workShift.getAction(i,e);return g==F.MORNING&&c>l&&a.push("Nel gruppo ".concat(o.name," al mattino sono richieste almeno ").concat(c," risorse, presenti ").concat(l)),g==F.AFTERNOON&&f>h&&(this.logger.info((function(){return"action ".concat(g," ").concat(F.AFTERNOON)})),a.push("Nel gruppo ".concat(o.name," al pomeriggio sono richieste almeno ").concat(f," risorse, presenti ").concat(h))),a}}]),n}(H),q=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(){return Object(o["a"])(this,n),e.apply(this,arguments)}return Object(s["a"])(n,[{key:"getGroup",value:function(t,e){return e.getGroup(t.id)}}]),n}(K),Q=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(){return Object(o["a"])(this,n),e.apply(this,arguments)}return Object(s["a"])(n,[{key:"getGroup",value:function(t,e){return e.getSubgroup(t.id)}}]),n}(K),X=function(){function t(){Object(o["a"])(this,t),this.logger=x.getLogger("services.ShiftValidationService"),this.dateService=tt.getInstance().getDateService()}return Object(s["a"])(t,[{key:"getEmployeeErrors",value:function(t,e,n){return this.errors(t,e,n,[new Z,new $,new J(F.MORNING),new J(F.AFTERNOON),new q,new Q])}},{key:"getCarsErrors",value:function(t,e,n){return this.errors(null,t,n,[new J(e)])}},{key:"errors",value:function(t,e,n,r){var a=this,i=r.flatMap((function(r){return r.errors(t,e,n)}));return this.logger.debug((function(){return"Validate employee ".concat(t," day ").concat(a.dateService.format(e)," errors ").concat(i)})),i}}]),t}(),tt=function(){function t(){Object(o["a"])(this,t),this._instances={}}return Object(s["a"])(t,[{key:"getActionService",value:function(){return this.getService("ActionService",(function(){return new P}))}},{key:"getDateService",value:function(){return this.getService("DateService",(function(){return new B}))}},{key:"getWorkShiftService",value:function(){return this.getService("WorkShiftService",(function(){return new z}))}},{key:"getShiftValidationService",value:function(){return this.getService("ShiftValidationService",(function(){return new X}))}},{key:"getService",value:function(t,e){if(!this._instances[t]){var n=e();this._instances[t]=n}return this._instances[t]}}],[{key:"getInstance",value:function(){return null==this._context&&(this._context=new t),this._context}}]),t}(),et=(n("fb6a"),n("4ec9"),function(){return window.performance&&window.performance.now&&window.performance.timing&&window.performance.timing.navigationStart?window.performance.now()+window.performance.timing.navigationStart:Date.now()}),nt=function(){function t(){Object(o["a"])(this,t),this.maxSize=1e3,this.ttl=300}return Object(s["a"])(t,[{key:"toString",value:function(){return"maxSize: ".concat(this.maxSize," ttl: ").concat(this.ttl)}}],[{key:"init",value:function(e,n){var r=new t;return r.maxSize=e,r.ttl=n,r}}]),t}(),rt=function t(e){Object(o["a"])(this,t),this.inserted=et(),this.value=e},at=function t(){Object(o["a"])(this,t),this.hits=0,this.miss=0,this.evict=0,this.size=0},it=function(){function t(){Object(o["a"])(this,t),this.logger=x.getLogger("utils.Cache.Cache"),this._entries=new Map,this._stats=new at,this.config=new nt}return Object(s["a"])(t,[{key:"get",value:function(t){var e;this.validateEntry(t);var n=null===(e=this._entries.get(t))||void 0===e?void 0:e.value;return"undefined"!==typeof n?(this.logger.debug((function(){return"HIT! ".concat(t)})),this._stats.hits++):(this.logger.debug((function(){return"MISS! ".concat(t)})),this._stats.miss++),n}},{key:"put",value:function(t,e){var n=this;this.size()>=this.config.maxSize&&this.cleanup(),this.logger.debug((function(){return"SIZE: ".concat(n.size())})),this._stats.size=this.size(),this._entries.set(t,new rt(e))}},{key:"evict",value:function(t){this.doEvict(t)}},{key:"validateEntry",value:function(t){if(this._entries.has(t)){var e=this._entries.get(t);et()-e.inserted>this.config.ttl&&(this.logger.debug((function(){return"Key is too old: ".concat(t)})),this.doEvict(t))}}},{key:"doEvict",value:function(t){this._stats.evict++,this._entries.delete(t)}},{key:"size",value:function(){return this._entries.size}},{key:"cleanup",value:function(){var t=this,e=Math.max(0,this.size()-this.config.maxSize);this.logger.debug((function(){return"Delete ".concat(e," entries")}));var n=Array.from(this._entries.entries()).sort((function(t,e){return t[1].inserted-e[1].inserted})).slice(0,e);n.map((function(e){return t.logger.debug((function(){return"Cleanup: ".concat(e[0])})),e})).forEach((function(e){return t.doEvict(e[0])}))}},{key:"stats",value:function(){return"HITS: ".concat(this._stats.hits," MISS: ").concat(this._stats.miss," EVICT: ").concat(this._stats.evict," SIZE: ").concat(this._stats.size)}},{key:"resetStats",value:function(){this._stats=new at}}]),t}(),ot=function(){function t(){Object(o["a"])(this,t),this.logger=x.getLogger("utils.Cache.CacheManager"),this._caches=new Map}return Object(s["a"])(t,[{key:"get",value:function(t){return this._caches.has(t)||this._caches.set(t,new it),this._caches.get(t)}},{key:"caches",get:function(){return this._caches}}],[{key:"getInstance",value:function(){return t._instance||(t._instance=new t),t._instance}}]),t}();setInterval((function(){var t=x.getLogger("utils.Cache.stats");Array.from(ot.getInstance().caches.entries()).forEach((function(e){t.info((function(){return"CACHE STATS [".concat(e[0],"] ").concat(e[1].stats()," ").concat(e[1].config)})),e[1].resetStats()}))}),2e4);n("13d5");var st=200,ut=5,ct=function(){function t(){Object(o["a"])(this,t),this._counter=0,this._longCallsCounter=0,this._durations=[]}return Object(s["a"])(t,[{key:"addDuration",value:function(t){this._durations.length>=st&&this._durations.shift(),this._durations.push(t),t>ut&&this._longCallsCounter++}},{key:"increaseCounter",value:function(){return++this._counter}},{key:"durations",value:function(){return this._durations}},{key:"avg",value:function(){var t=this._durations.reduce((function(t,e){return t+e}),0);return t/this._durations.length}},{key:"weight",value:function(){return this.counter*this.avg()}},{key:"counter",get:function(){return this._counter}},{key:"longCalls",get:function(){return this._longCallsCounter}}]),t}(),lt=function(){function t(){Object(o["a"])(this,t),this.logger=x.getLogger("utils.Stats.StatsCollector"),this._entries=new Map}return Object(s["a"])(t,[{key:"addEntry",value:function(t,e){this._entries.has(t)||this._entries.set(t,new ct);var n=this._entries.get(t);n.increaseCounter(),n.addDuration(e)}},{key:"resetEntry",value:function(t){this._entries.delete(t)}},{key:"entries",get:function(){return this._entries}}],[{key:"getInstance",value:function(){return t._instance||(t._instance=new t),t._instance}},{key:"addEntry",value:function(e,n){t.getInstance().addEntry(e,n)}}]),t}();setInterval((function(){if(0!=lt.getInstance().entries.size){var t=x.getLogger("utils.Stats.stats");t.info((function(){return"Count\t|\tLong\t|\tAvg\t|\tWeight\t|\tName"})),Array.from(lt.getInstance().entries.entries()).sort((function(t,e){return t[1].weight()-e[1].weight()})).reverse().forEach((function(e){t.info((function(){return"".concat(e[1].counter,"\t|\t").concat(e[1].longCalls,"\t|\t").concat(e[1].avg().toPrecision(4),"\t|\t").concat(e[1].weight().toPrecision(4),"\t|\t").concat(e[0])})),lt.getInstance().resetEntry(e[0])}))}}),2e4);var ft=function(){return window.performance&&window.performance.now&&window.performance.timing&&window.performance.timing.navigationStart?window.performance.now()+window.performance.timing.navigationStart:Date.now()};function ht(){var t=x.getLogger("utils.Decorators.stats");return function(e,n,r){var a=r.value;return r.value=function(){t.debug((function(){return"Call ".concat(n," start")}));var e=ft();try{for(var r=arguments.length,i=new Array(r),o=0;o<r;o++)i[o]=arguments[o];var s=a.apply(this,i),u=ft()-e;return t.debug((function(){return"Call ".concat(n," end [").concat(u,"]")})),lt.addEntry(n,u),s}catch(l){var c=ft()-e;throw t.error((function(){return"Call ".concat(n," failed [").concat(c,"]")})),l}},r}}function gt(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:function(t){return t},n=arguments.length>2?arguments[2]:void 0;x.getLogger("utils.Decorators.cacheable");return function(r,a,i){var o=i.value;return i.value=function(){var r=ot.getInstance().get(t);n&&(r.config=n);for(var a=arguments.length,i=new Array(a),s=0;s<a;s++)i[s]=arguments[s];var u=e(i),c=r.get(u);if("undefined"!==typeof c)return c;var l=o.apply(this,i);return r.put(u,l),l},i}}var vt=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(){var t;return Object(o["a"])(this,n),t=e.call(this),t.logger=x.getLogger("components.WorkShiftTable"),t}return Object(s["a"])(n,[{key:"increment",value:function(){var t=this;this.$store.commit("increment"),this.logger.info((function(){return"Store, increment: ".concat(t.$store.state.count)}))}},{key:"handleUpdateFromDate",value:function(t){this.logger.info((function(){return"Update from date: ".concat(t)}));var e=tt.getInstance().getDateService(),n=e.parse(t),r=e.getStartOfMonth(n),a=e.getEndOfMonth(n);this.context.from=e.getStartOfWeek(r),this.context.to=e.getEndOfWeek(a)}},{key:"handleShift",value:function(t,e,n){this.logger.info((function(){return"New shift, employee ".concat(t,", day ").concat(e,", value=").concat(n)}));var r=this.context.getEmployee(t),a=this.context.workShift.key(r,e);f["c"].set(this.context.workShift.shift,a,n)}},{key:"getShift",value:function(t,e){var n=this.context.getEmployee(t);return this.context.workShift.getValue(n,e)}},{key:"getEmployeeErrors",value:function(t,e){return tt.getInstance().getShiftValidationService().getEmployeeErrors(t,e,this.context)}},{key:"hasEmployeeErrors",value:function(t,e){return this.getEmployeeErrors(t,e).length>0}},{key:"getCarsErrors",value:function(t,e){return tt.getInstance().getShiftValidationService().getCarsErrors(e,t,this.context)}},{key:"hasCarsErrors",value:function(t,e){return this.getCarsErrors(t,e).length>0}},{key:"employees",value:function(){return this.context.sortedEmployees()}},{key:"formatHeaderDate",value:function(t){return tt.getInstance().getDateService().formatShort(t)}},{key:"getDayOfWeekLabel",value:function(t){return tt.getInstance().getDateService().getDayOfWeekLabel(t)}},{key:"days",value:function(){return tt.getInstance().getDateService().range(this.context.from,this.context.to).length}},{key:"day",value:function(t){var e=tt.getInstance().getDateService().range(this.context.from,this.context.to)[t-1];return e}},{key:"isEndOfWeek",value:function(t){return tt.getInstance().getDateService().isEndOfWeek(t)}},{key:"isStartOfSubgroup",value:function(t){if(this.isStartOfGroup(t))return!1;var e=this.employees(),n=e.findIndex((function(e){return e==t}));if(0==n)return!0;var r=e[n-1],a=this.context.getEmployee(t),i=this.context.getEmployee(r);return a.subgroupId!=i.subgroupId}},{key:"isStartOfGroup",value:function(t){var e=this.employees(),n=e.findIndex((function(e){return e==t}));if(0==n)return!0;var r=e[n-1],a=this.context.getGroup(t),i=this.context.getGroup(r);return(null===a||void 0===a?void 0:a.id)!=(null===i||void 0===i?void 0:i.id)}},{key:"getSubgroupName",value:function(t){var e=this.context.getSubgroup(t);return e?e.name:""}},{key:"getGroupName",value:function(t){var e=this.context.getGroup(t);return e?e.name:""}},{key:"getUsedCars",value:function(t,e){var n=tt.getInstance().getWorkShiftService(),r=n.countByEmployeesDatesAction(this.employees(),[t],e,this.context);return n.countByEmployeesDatesAction(this.employees(),[t],e,this.context),r}},{key:"getTotCars",value:function(){return this.context.availableCars.total}},{key:"morningAction",value:function(){return F.MORNING}},{key:"afternoonAction",value:function(){return F.AFTERNOON}}]),n}(f["c"]);Object(l["a"])([Object(f["b"])()],vt.prototype,"context",void 0),Object(l["a"])([Object(f["b"])()],vt.prototype,"availableStates",void 0),Object(l["a"])([ht()],vt.prototype,"getShift",null),Object(l["a"])([gt("WorkShiftTable.getEmployeeErrors",(function(t){return t[0]+"_"+t[1].toISOString()})),ht()],vt.prototype,"getEmployeeErrors",null),Object(l["a"])([ht()],vt.prototype,"hasEmployeeErrors",null),Object(l["a"])([ht()],vt.prototype,"getCarsErrors",null),Object(l["a"])([ht()],vt.prototype,"hasCarsErrors",null),Object(l["a"])([ht()],vt.prototype,"employees",null),Object(l["a"])([ht()],vt.prototype,"formatHeaderDate",null),Object(l["a"])([ht()],vt.prototype,"getDayOfWeekLabel",null),Object(l["a"])([ht()],vt.prototype,"days",null),Object(l["a"])([gt("WorkShiftTable.day",(function(t){return t[0]}),nt.init(40,10)),ht()],vt.prototype,"day",null),Object(l["a"])([ht()],vt.prototype,"isEndOfWeek",null),Object(l["a"])([gt("WorkShiftTable.isStartOfSubgroup",(function(t){return t[0]})),ht()],vt.prototype,"isStartOfSubgroup",null),Object(l["a"])([gt("WorkShiftTable.isStartOfGroup",(function(t){return t[0]})),ht()],vt.prototype,"isStartOfGroup",null),Object(l["a"])([ht()],vt.prototype,"getUsedCars",null),vt=Object(l["a"])([Object(f["a"])({components:{WorkShiftInput:D,MonthPicker:Y}})],vt);var pt=vt,dt=pt,yt=(n("05c0"),n("62ad")),bt=n("a523"),kt=n("1f4f"),mt=n("3a2f9"),Ot=Object(k["a"])(dt,h,g,!1,null,"df8ae126",null),St=Ot.exports;O()(Ot,{VCol:yt["a"],VContainer:bt["a"],VRow:W["a"],VSimpleTable:kt["a"],VTooltip:mt["a"]});var _t,wt=function(){function t(){Object(o["a"])(this,t),this._total=0}return Object(s["a"])(t,[{key:"total",set:function(t){this._total=t},get:function(){return this._total}}]),t}(),Et=function(){function t(){Object(o["a"])(this,t),this._shifts={}}return Object(s["a"])(t,[{key:"getValue",value:function(t,e){var n=this.key(t,e),r=this._shifts[n];return r||tt.getInstance().getActionService().getDefaultLabel()}},{key:"getAction",value:function(t,e){var n=this.getValue(t,e);return tt.getInstance().getActionService().getAction(n)}},{key:"key",value:function(t,e){return t.id+"_"+tt.getInstance().getDateService().format(e)}},{key:"shift",get:function(){return this._shifts},set:function(t){this._shifts=t}}]),t}(),At=function(){function t(){Object(o["a"])(this,t),this._from=new Date,this._to=new Date,this._employees=new Map,this._groups=new Map,this._subgroups=new Map,this._availableCars=new wt,this._workShift=new Et}return Object(s["a"])(t,[{key:"getEmployee",value:function(t){if(this.employees.has(t))return this.employees.get(t);throw new Error("Employee "+t+" unknown.")}},{key:"sortedEmployees",value:function(){var t=this,e=Array.from(this.employees.values());return e.sort((function(e,n){var r=e.subgroupId,a=n.subgroupId;if(!r)return-1;if(!a)return 1;if(r!=a)return r-a;var i=t.subgroups.get(r),o=t.subgroups.get(a),s=0;i&&(s=i.parent);var u=0;return o&&(u=o.parent),s!=u?s-u:e.id-n.id})),e.map((function(t){return t.id}))}},{key:"getSubgroup",value:function(t){var e=this.getEmployee(t).subgroupId;if(e)return this.subgroups.get(e)}},{key:"getGroup",value:function(t){var e=this.getSubgroup(t);if(e&&e.parent)return this.groups.get(e.parent)}},{key:"from",set:function(t){this._from=t},get:function(){return this._from}},{key:"to",set:function(t){this._to=t},get:function(){return this._to}},{key:"employees",set:function(t){this._employees=t},get:function(){return this._employees}},{key:"availableCars",set:function(t){this._availableCars=t},get:function(){return this._availableCars}},{key:"workShift",set:function(t){this._workShift=t},get:function(){return this._workShift}},{key:"groups",set:function(t){this._groups=t},get:function(){return this._groups}},{key:"subgroups",set:function(t){this._subgroups=t},get:function(){return this._subgroups}}]),t}(),Dt=function(){function t(){Object(o["a"])(this,t),this._id=0,this._name="",this._subgroupId=null,this.totWeekShifts=0,this.maxWeekMornings=0,this.maxWeekAfternoons=0}return Object(s["a"])(t,[{key:"name",set:function(t){this._name=t},get:function(){return this._name}},{key:"id",set:function(t){this._id=t},get:function(){return this._id}},{key:"subgroupId",set:function(t){this._subgroupId=t},get:function(){return this._subgroupId}}]),t}(),jt=function(){function t(){Object(o["a"])(this,t),this._constraints=new Map}return Object(s["a"])(t,[{key:"clone",value:function(){var e=new t;return Array.from(this._constraints.entries()).forEach((function(t){e._constraints.set(t[0],t[1])})),e}},{key:"setMin",value:function(t,e,n){this._constraints.set(this.key(t,e,"min"),n)}},{key:"getMin",value:function(t,e){var n=this._constraints.get(this.key(t,e,"min"));return"undefined"===typeof n?0:n}},{key:"key",value:function(t,e,n){return"".concat(t,"_").concat(e,"_").concat(n)}}]),t}(),It=function(){function t(){Object(o["a"])(this,t),this._instance=new jt}return Object(s["a"])(t,[{key:"setMin",value:function(t,e,n){return this._instance.setMin(t,e,n),this}},{key:"build",value:function(){return this._instance.clone()}}],[{key:"newInstance",value:function(){return new t}}]),t}(),Nt=function(){function t(){Object(o["a"])(this,t),this._id=0,this._name="",this._constraints=new jt}return Object(s["a"])(t,[{key:"clone",value:function(){var e=new t;return e.id=this.id,e.name=this.name,e}},{key:"name",set:function(t){this._name=t},get:function(){return this._name}},{key:"id",set:function(t){this._id=t},get:function(){return this._id}},{key:"constraints",set:function(t){this._constraints=t},get:function(){return this._constraints}}]),t}(),Ct=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(){return Object(o["a"])(this,n),e.call(this)}return Object(s["a"])(n,[{key:"parent",set:function(t){this._parent=t},get:function(){if(void 0===this._parent)throw Error("Missing parent for ".concat(this));return this._parent}}]),n}(Nt),xt=_t=function(t){Object(u["a"])(n,t);var e=Object(c["a"])(n);function n(){var t;Object(o["a"])(this,n),t=e.call(this);var r=tt.getInstance().getDateService(),a=new At;return t.context=a,a.from=r.parse("2020-02-04"),a.to=r.parse("2020-02-05"),_t.group(a,1,"Macro1",_t.weekConstraint(0,1,1,1)),_t.group(a,2,"Macro2",_t.weekConstraint(0,1,1,1)),_t.group(a,3,"Macro3",_t.weekConstraint(0,0,0,0)),_t.subgroup(a,1,"Mogliano",1,_t.weekConstraint(1,0,0,0)),_t.subgroup(a,2,"Preganziol",1,_t.weekConstraint(1,0,0,0)),_t.subgroup(a,3,"Casale / Roncade",1,_t.weekConstraint(1,0,0,0)),_t.subgroup(a,4,"S.Bona",2,_t.weekConstraint(1,0,0,0)),_t.subgroup(a,5,"c.città",2,_t.weekConstraint(1,0,0,0)),_t.subgroup(a,6,"S.Zeno",2,_t.weekConstraint(1,0,0,0)),_t.subgroup(a,7,"S.Biagio Silea",2,_t.weekConstraint(1,0,0,0)),_t.subgroup(a,8,"CP",3,_t.weekConstraint(1,0,0,0)),_t.employee(a,1,"ALBORE P. SANDRO",1,5,5,2),_t.employee(a,2,"CHINELLATO MONIA",1,5,5,2),_t.employee(a,3,"GIACOMIN BARBARA",1,5,5,0),_t.employee(a,4,"RIGO FRANCESCA",2,5,5,2),_t.employee(a,5,"BARBATO GIUSEPPE",2,5,5,2),_t.employee(a,6,"VIALE MARTA",2,5,5,2),_t.employee(a,7,"COSTANTINI RENZO",3,5,5,2),_t.employee(a,8,"BERTON ELISABETTA",3,4,4,2),_t.employee(a,9,"TOZZATO MARTINA",3,5,5,2),_t.employee(a,10,"BERALDO SILVIA",4,5,5,2),_t.employee(a,11,"CITTON MARZIA",4,5,5,2),_t.employee(a,12,"CERON CESARE",4,5,5,2),_t.employee(a,13,"VANIN STEFANIA",5,5,5,2),_t.employee(a,14,"SERAFIN SERENA",5,5,5,2),_t.employee(a,15,"MAREN FABIO",5,5,5,2),_t.employee(a,16,"TONON CHRISTIAN",6,5,5,2),_t.employee(a,17,"CARPIN ANNALISA",6,5,5,2),_t.employee(a,18,"SPIGARIOL PAOLO",7,5,5,2),_t.employee(a,19,"VIANELLO MARTA",7,5,5,2),_t.employee(a,20,"BOT CLAUDIA",7,4,4,2),_t.employee(a,21,"BEZZI SILVIA",8,5,5,2),_t.employee(a,22,"BRUNELLO VANIA",8,5,5,2),a.availableCars.total=2,t}return Object(s["a"])(n,null,[{key:"employee",value:function(t,e,n,r,a,i,o){var s=new Dt;return s.id=e,s.name=n,s.totWeekShifts=a,s.maxWeekMornings=i,s.maxWeekAfternoons=o,s.subgroupId=r,t.employees.set(e,s),s}},{key:"group",value:function(t,e,n,r){var a=new Nt;return a.id=e,a.name=n,a.constraints=r.build(),t.groups.set(e,a),a}},{key:"subgroup",value:function(t,e,n,r,a){var i=new Ct;return i.id=e,i.name=n,i.parent=r,i.constraints=a.build(),t.subgroups.set(e,i),i}},{key:"weekConstraint",value:function(t,e,n,r){var a=It.newInstance(),i=[U.MONDAY,U.TUESDAY,U.WEDNESDAY,U.THURSDAY,U.FRIDAY],o=[U.SATURDAY,U.SUNDAY];return i.forEach((function(n){a.setMin(n,F.MORNING,t),a.setMin(n,F.AFTERNOON,e)})),o.forEach((function(t){a.setMin(t,F.MORNING,n),a.setMin(t,F.AFTERNOON,r)})),a}}]),n}(f["c"]);xt=_t=Object(l["a"])([Object(f["a"])({components:{WorkShiftTable:St}})],xt);var Mt=xt,Rt=Mt,Lt=(n("5c0b"),n("7496")),Tt=n("40dc"),Wt=n("8336"),Vt=n("a75b"),Gt=n("132d"),Yt=n("adda"),Ft=n("2fa4"),Ut=Object(k["a"])(Rt,a,i,!1,null,null,null),Pt=Ut.exports;O()(Ut,{VApp:Lt["a"],VAppBar:Tt["a"],VBtn:Wt["a"],VContent:Vt["a"],VIcon:Gt["a"],VImg:Yt["a"],VSpacer:Ft["a"]});var Bt=n("2f62");r["default"].use(Bt["a"]);var zt=new Bt["a"].Store({state:{count:0,fromDate:(new Date).toISOString().slice(0,10),toDate:(new Date).toISOString().slice(0,10)},mutations:{increment:function(t){t.count++},updateFromDate:function(t,e){t.fromDate=e},updateToDate:function(t,e){t.toDate=e}},actions:{},modules:{}}),Ht=n("ce5b"),Zt=n.n(Ht),$t=(n("bf40"),n("f309"));r["default"].use($t["a"]);var Jt=new $t["a"]({});r["default"].use(Zt.a),r["default"].config.productionTip=!1,new r["default"]({store:zt,vuetify:Jt,render:function(t){return t(Pt)}}).$mount("#app")}});
//# sourceMappingURL=app.1ea71cab.js.map