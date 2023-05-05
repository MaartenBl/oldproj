<template>
  <div>
    <div class="row">
      <SideBar/>

      <div class="wrapper">
        <p>Record a Run</p>

        <form action="">
          <input type="text" placeholder="Name (Optional)">
          <input class="sensID" v-model="sensorbox" type="text" placeholder="Sensor ID"><button v-on:click="addSensor">Add</button>
          <ul>
            <li
              is="smallList"
              v-for="(item, index) in this.sensorIDs"
              v-bind:key="item.tagId"
              v-bind:item="item"
              v-on:remove="this.sensorIDs.splice(index, 1)"
            ></li>
          </ul>
          <div class="row col-sm-12">
            <button v-on:click="startListening" class="">Record</button>
            <button v-on:click="saveFile" class="">Stop</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>


<script>
import SideBar from '@/components/SideBar.vue'
import smallList from '../components/smallList.vue'
import mqtt from 'mqtt'

export default {
  name: 'Record',
  props: {sensorIDs: { type: Array, default: () => { return [] } }}, 

  components: {
    SideBar,
    smallList,
  },

  data() {
    return{
      host: 'ws://localhost:9001',
      p_user: '6054749c28775c64b2043824',
      p_pass: 'ee886814-2fcf-4e5f-8022-8ec2f3ccc539',
      p_topic: '6054749c28775c64b2043824',
      sensorbox: '',
      stringsArray: [],
      client: '',
    }
  },

  methods: {
    addSensor() {
      if (!this.sensorIDs.some(x => x.tagId == this.sensorbox))
        this.sensorIDs.push({ tagId: this.sensorbox });
      this.sensorbox = '';
    },

    startListening(){
      const opts = {
        clientId: 'SMC-Frontend',
        username: this.p_user,
        password: this.p_pass
      }

      let client = mqtt.connect(this.host);

      this.client = client
      var sensorList = this.sensorIDs;

      client.on('connect', function () {
        client.subscribe('tags', function (err) {
          if (!err) {
            client.publish('presence', 'SMC-Frontend Connected')
          } else {
            console.log(err);
          }
        })
      });

      client.on('message', function (topic, message) {
        var messageIn = message.toString();
        var tempArr = JSON.parse(messageIn);

        console.log(message);

        tempArr.forEach(tagPoint => 
        { 
          let temp = sensorList.find(obj => obj.tagId == tagPoint.tagId);
          if(temp)
          {
            if (!('points' in temp))
              temp.points = [];
            if(tagPoint.success)
            {
              if(!temp.version){
                temp.version = tagPoint.version;
              }
              tagPoint.ts = tagPoint.timestamp;
              delete tagPoint.timestamp;
              delete tagPoint.version;
              delete tagPoint.data.metrics;
              delete tagPoint.data.type;
              delete tagPoint.tagId;
              delete tagPoint.data.anchorData;
              delete tagPoint.data.tagData.accelerometer;
              delete tagPoint.data.events;
              delete tagPoint.success;
              delete Object.assign(tagPoint.data, {[coords]: tagPoint.data[coordinates] })[coordinates];
              tagPoint.data.coords.x /= 1000;
              tagPoint.data.coords.y /= 1000;
              tagPoint.data.coords.z /= 1000;
              temp.points.push(tagPoint);
            }else{
              if(!temp.errorList){
                temp.errorList = [];
              }
              errorList.push(tagPoint);
            }
          }
        });

      }) 
    },

    saveFile(){
      this.client.end();

      let sensorList2 = this.sensorIDs;

      sensorList2.forEach(sensObj => { 
        if(sensObj.points === undefined || sensObj.tagId === undefined){
          console.log('No tag ID. || No points added.');
        }
        else{
          sensObj.points.sort((first, second) => {return first.ts - second.ts;});
          sensObj.ClbrOffset = { x: 0, y: 0, z: 0 };
          for (let index = 0; index < sensObj.points.length; index++) {
            const element = sensObj.points[index].data;
            const lastElement = sensObj.points[index -1].data;
            if(!lastElement) {
              element.velo ={ x: 0, y: 0, z: 0 };
              element.accel = { x: 0, y: 0, z: 0 };
            }
            else {
              let deltaT = (sensObj.points[index].ts - sensObj.points[index -1].ts);
              element.velo = { x: (element.coordinates.x - lastElement.coordinates.x)/deltaT, y: (element.coordinates.y - lastElement.coordinates.y)/deltaT, z: (element.coordinates.z - lastElement.coordinates.z)/deltaT };
              element.speed = Math.sqrt(element.velo.x*element.velo.x+ element.velo.y*element.velo.y+element.velo.z*element.velo.z);
              element.accel = { x: (element.velo.x - lastElement.velo.x)/deltaT, y: (element.velo.y - lastElement.velo.y)/deltaT, z: (element.velo.z - lastElement.velo.z)/deltaT };
            }
          }
        }
      });

      let today = new Date();
      let Run = { RiderId : 0, EndOfRun : today, sensors : sensorList2 };

      // Saving file
      const data = JSON.stringify(Run);
      const blob = new Blob([data], {type: 'text/plain'});
      const e = document.createEvent('MouseEvents'),
      a = document.createElement('a');
      var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
      var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
      var dateTime = date+' '+time;
      a.download = dateTime + ".json";
      a.href = window.URL.createObjectURL(blob);
      a.dataset.downloadurl = ['text/json', a.download, a.href].join(':');
      e.initEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
      a.dispatchEvent(e);
    }
  },
}
</script>

<style scoped>

.wrapper{
  position: fixed;
  left: 50%;
  top: 30%;

  width: 20%;
  /* height: 30%; */

  background: #fff;
  border: 1px solid black;
  box-shadow: 0 6px 12px #999;
}

.wrapper p{
  padding: 20px 30px 0;
  font-weight: 100;
  font-size: 32px;
}

form{
  padding-left: 30px;
}

form input{
  width: 80%;
  margin-bottom: 10px;
  padding: 5px;
}

form button{
  width: 20%;
  padding: 10px;
  margin: 10px 15px;

  color: white;
  background: #00887A;
  border: none;
  border-radius: 5px;
}

</style>