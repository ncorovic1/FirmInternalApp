<template>
    <div>
        <h1 class="text-center"> {{ document.title }}                                    </h1>
        <h4 class="text-center"> Author: {{ document.author.firstName }}                 </h4>
        <h5 class="text-center"> Created at: {{ createDate(document.createdAt) }}        </h5>
        <h5 class="text-center"> Last modified at: {{ createDate(document.modifiedAt) }} </h5>
        <div class="content" v-html="document.content"> </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                document: {
                    title: '',
                    content: '',
                    createdAt: '',
                    modifiedAt: '',
                    author: {
                        firstName: ''
                    }
                }
            }
        },
        methods: {
            createDate(d) {
                if (d == '')
                    return '';
                var time = new Date(d);
                time.setHours(time.getHours() + 2);
                return time.toISOString().substring(0, 19).replace('T', ' ');
            }
        },
        created() {
            var url = window.location.href.split('/');
            var id = url[url.length - 1];
            this.$http.get('http://localhost:8084/documents/' + id)
                .then(response => {
                    this.document = response.body;
                });
        }
    }
</script>

<style scoped>
    .content {
        margin-top: 20px;
        background-color: lightblue;
        border: 2px solid black;
        border-radius: 10px;
    }
    
    .timeline {
      list-style: none;
      padding: 20px 0 20px;
      position: relative;
    }

    .timeline:before {
      top: 0;
      bottom: 0;
      position: absolute;
      content: " ";
      width: 3px;
      background-color: #eeeeee;
      left: 50%;
      margin-left: -1.5px;
    }

    .timeline > li {
      margin-bottom: 20px;
      position: relative;
    }

    .timeline > li:before,
    .timeline > li:after {
      content: " ";
      display: table;
    }

    .timeline > li:after {
      clear: both;
    }

    .timeline > li:before,
    .timeline > li:after {
      content: " ";
      display: table;
    }

    .timeline > li:after {
      clear: both;
    }

    .timeline > li > .timeline-panel {
      width: 46%;
      float: left;
      border: 1px solid #d4d4d4;
      border-radius: 2px;
      padding: 20px;
      position: relative;
      -webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
      box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
    }

    .timeline > li > .timeline-panel:before {
      position: absolute;
      top: 26px;
      right: -15px;
      display: inline-block;
      border-top: 15px solid transparent;
      border-left: 15px solid #ccc;
      border-right: 0 solid #ccc;
      border-bottom: 15px solid transparent;
      content: " ";
    }

    .timeline > li > .timeline-panel:after {
      position: absolute;
      top: 27px;
      right: -14px;
      display: inline-block;
      border-top: 14px solid transparent;
      border-left: 14px solid #fff;
      border-right: 0 solid #fff;
      border-bottom: 14px solid transparent;
      content: " ";
    }

    .timeline > li > .timeline-badge {
      color: #fff;
      width: 50px;
      height: 50px;
      line-height: 50px;
      font-size: 1.4em;
      text-align: center;
      position: absolute;
      top: 16px;
      left: 50%;
      margin-left: -25px;
      background-color: #999999;
      z-index: 100;
      border-top-right-radius: 50%;
      border-top-left-radius: 50%;
      border-bottom-right-radius: 50%;
      border-bottom-left-radius: 50%;
    }

    .timeline > li.timeline-inverted > .timeline-panel {
      float: right;
    }

    .timeline > li.timeline-inverted > .timeline-panel:before {
      border-left-width: 0;
      border-right-width: 15px;
      left: -15px;
      right: auto;
    }

    .timeline > li.timeline-inverted > .timeline-panel:after {
      border-left-width: 0;
      border-right-width: 14px;
      left: -14px;
      right: auto;
    }

    .timeline-badge.primary {
      background-color: #2e6da4 !important;
    }

    .timeline-badge.success {
      background-color: #3f903f !important;
    }

    .timeline-badge.warning {
      background-color: #f0ad4e !important;
    }

    .timeline-badge.danger {
      background-color: #d9534f !important;
    }

    .timeline-badge.info {
      background-color: #5bc0de !important;
    }

    .timeline-title {
      margin-top: 0;
      color: inherit;
    }

    .timeline-body > p,
    .timeline-body > ul {
      margin-bottom: 0;
    }

    .timeline-body > p + p {
      margin-top: 5px;
    }
    //////////////
    // RESUMEEE
    //////////////

    .page-header{
      text-align: center;    
    }

    /*social buttons*/
    .btn-social{
      color: white;
      opacity:0.9;
    }
    .btn-social:hover {
      color: white;
        opacity:1;
    }
    .btn-facebook {
    background-color: #3b5998;
    opacity:0.9;
    }
    .btn-twitter {
    background-color: #00aced;
    opacity:0.9;
    }
    .btn-linkedin {
    background-color:#0e76a8;
    opacity:0.9;
    }
    .btn-github{
      background-color:#000000;
      opacity:0.9;
    }
    .btn-google {
      background-color: #c32f10;
      opacity: 0.9;
    }
    .btn-stackoverflow{
      background-color: #D38B28;
      opacity: 0.9;
    }

    /* resume stuff */

    .bs-callout {
        -moz-border-bottom-colors: none;
        -moz-border-left-colors: none;
        -moz-border-right-colors: none;
        -moz-border-top-colors: none;
        border-color: #eee;
        border-image: none;
        border-radius: 3px;
        border-style: solid;
        border-width: 1px 1px 1px 5px;
        margin-bottom: 5px;
        padding: 20px;
    }
    .bs-callout:last-child {
        margin-bottom: 0px;
    }
    .bs-callout h4 {
        margin-bottom: 10px;
        margin-top: 0;
    }

    .bs-callout-danger {
        border-left-color: #d9534f;
    }

    .bs-callout-danger h4{
        color: #d9534f;
    }

    .resume .list-group-item:first-child, .resume .list-group-item:last-child{
      border-radius:0;
    }

    /*makes an anchor inactive(not clickable)*/
    .inactive-link {
       pointer-events: none;
       cursor: default;
    }

    .resume-heading .social-btns{
      margin-top:15px;
    }
    .resume-heading .social-btns i.fa{
      margin-left:-5px;
    }

    @media (max-width: 992px) {
      .resume-heading .social-btn-holder{
        padding:5px;
      }
    }


    /* skill meter in resume. copy pasted from http://bootsnipp.com/snippets/featured/progress-bar-meter */

    .progress-bar {
        text-align: left;
        white-space: nowrap;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        cursor: pointer;
    }

    .progress-bar > .progress-type {
        padding-left: 10px;
    }

    .progress-meter {
        min-height: 15px;
        border-bottom: 2px solid rgb(160, 160, 160);
      margin-bottom: 15px;
    }

    .progress-meter > .meter {
        position: relative;
        float: left;
        min-height: 15px;
        border-width: 0px;
        border-style: solid;
        border-color: rgb(160, 160, 160);
    }

    .progress-meter > .meter-left {
        border-left-width: 2px;
    }

    .progress-meter > .meter-right {
        float: right;
        border-right-width: 2px;
    }

    .progress-meter > .meter-right:last-child {
        border-left-width: 2px;
    }

    .progress-meter > .meter > .meter-text {
        position: absolute;
        display: inline-block;
        bottom: -20px;
        width: 100%;
        font-weight: 700;
        font-size: 0.85em;
        color: rgb(160, 160, 160);
        text-align: left;
    }

    .progress-meter > .meter.meter-right > .meter-text {
        text-align: right;
    }

body {
        padding-top: 40px;
        padding-bottom: 40px;
    	
      }
	
	/* COMMON PRICING STYLES */
		.panel.price,
		.panel.price>.panel-heading{
			border-radius:0px;
			 -moz-transition: all .3s ease;
			-o-transition:  all .3s ease;
			-webkit-transition:  all .3s ease;
		}
		.panel.price:hover{
			box-shadow: 0px 0px 30px rgba(0,0,0, .2);
		}
		.panel.price:hover>.panel-heading{
			box-shadow: 0px 0px 30px rgba(0,0,0, .2) inset;
		}
		
				
		.panel.price>.panel-heading{
			box-shadow: 0px 5px 0px rgba(50,50,50, .2) inset;
			text-shadow:0px 3px 0px rgba(50,50,50, .6);
		}
			
		.price .list-group-item{
			border-bottom-:1px solid rgba(250,250,250, .5);
		}
		
		.panel.price .list-group-item:last-child {
			border-bottom-right-radius: 0px;
			border-bottom-left-radius: 0px;
		}
		.panel.price .list-group-item:first-child {
			border-top-right-radius: 0px;
			border-top-left-radius: 0px;
		}
		
		.price .panel-footer {
			color: #fff;
			border-bottom:0px;
			background-color:  rgba(0,0,0, .1);
			box-shadow: 0px 3px 0px rgba(0,0,0, .3);
		}
		
		
		.panel.price .btn{
			box-shadow: 0 -1px 0px rgba(50,50,50, .2) inset;
			border:0px;
		}
		
	/* green panel */
	
		
		.price.panel-green>.panel-heading {
			color: #fff;
			background-color: #57AC57;
			border-color: #71DF71;
			border-bottom: 1px solid #71DF71;
		}
		
			
		.price.panel-green>.panel-body {
			color: #fff;
			background-color: #65C965;
		}
				
		
		.price.panel-green>.panel-body .lead{
				text-shadow: 0px 3px 0px rgba(50,50,50, .3);
		}
		
		.price.panel-green .list-group-item {
			color: #333;
			background-color: rgba(50,50,50, .01);
			font-weight:600;
			text-shadow: 0px 1px 0px rgba(250,250,250, .75);
		}
		
		/* blue panel */
	
		
		.price.panel-blue>.panel-heading {
			color: #fff;
			background-color: #608BB4;
			border-color: #78AEE1;
			border-bottom: 1px solid #78AEE1;
		}
		
			
		.price.panel-blue>.panel-body {
			color: #fff;
			background-color: #73A3D4;
		}
				
		
		.price.panel-blue>.panel-body .lead{
				text-shadow: 0px 3px 0px rgba(50,50,50, .3);
		}
		
		.price.panel-blue .list-group-item {
			color: #333;
			background-color: rgba(50,50,50, .01);
			font-weight:600;
			text-shadow: 0px 1px 0px rgba(250,250,250, .75);
		}
		
		/* red price */
		
	
		.price.panel-red>.panel-heading {
			color: #fff;
			background-color: #D04E50;
			border-color: #FF6062;
			border-bottom: 1px solid #FF6062;
		}
		
			
		.price.panel-red>.panel-body {
			color: #fff;
			background-color: #EF5A5C;
		}
		
		
		
		
		.price.panel-red>.panel-body .lead{
				text-shadow: 0px 3px 0px rgba(50,50,50, .3);
		}
		
		.price.panel-red .list-group-item {
			color: #333;
			background-color: rgba(50,50,50, .01);
			font-weight:600;
			text-shadow: 0px 1px 0px rgba(250,250,250, .75);
		}
		
		/* grey price */
		
	
		.price.panel-grey>.panel-heading {
			color: #fff;
			background-color: #6D6D6D;
			border-color: #B7B7B7;
			border-bottom: 1px solid #B7B7B7;
		}
		
			
		.price.panel-grey>.panel-body {
			color: #fff;
			background-color: #808080;
		}
		

		
		.price.panel-grey>.panel-body .lead{
				text-shadow: 0px 3px 0px rgba(50,50,50, .3);
		}
		
		.price.panel-grey .list-group-item {
			color: #333;
			background-color: rgba(50,50,50, .01);
			font-weight:600;
			text-shadow: 0px 1px 0px rgba(250,250,250, .75);
		}
		
		/* white price */
		
	
		.price.panel-white>.panel-heading {
			color: #333;
			background-color: #f9f9f9;
			border-color: #ccc;
			border-bottom: 1px solid #ccc;
			text-shadow: 0px 2px 0px rgba(250,250,250, .7);
		}
		
		.panel.panel-white.price:hover>.panel-heading{
			box-shadow: 0px 0px 30px rgba(0,0,0, .05) inset;
		}
			
		.price.panel-white>.panel-body {
			color: #fff;
			background-color: #dfdfdf;
		}
				
		.price.panel-white>.panel-body .lead{
				text-shadow: 0px 2px 0px rgba(250,250,250, .8);
				color:#666;
		}
		
		.price:hover.panel-white>.panel-body .lead{
				text-shadow: 0px 2px 0px rgba(250,250,250, .9);
				color:#333;
		}
		
		.price.panel-white .list-group-item {
			color: #333;
			background-color: rgba(50,50,50, .01);
			font-weight:600;
			text-shadow: 0px 1px 0px rgba(250,250,250, .75);
		}
</style>