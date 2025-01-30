<template>
  <div id="app">
    <header>
      <img alt="Vue logo" src="./assets/logo.png">
      <h1>Welcome to Your Customized Vue.js App</h1>
    </header>
    <main>
      <HelloWorld :msg="message"/>
      <p>Here you can add more content and components.</p>
    </main>
    <footer>
      <p>&copy; 2025 Your Company</p>
    </footer>
  </div>
</template>

<script>
import axios from 'axios';
import HelloWorld from './components/HelloWorld.vue';

export default {
  name: 'App',
  components: {
    HelloWorld
  },
  data() {
    return {
      message: 'Loading...'
    };
  },
  created() {
    this.fetchMessage();
  },
  methods: {
    fetchMessage() {
      axios.get('http://localhost:8080/api/message')
        .then(response => {
          this.message = response.data.message;
        })
        .catch(error => {
          console.error('Error fetching message:', error);
          this.message = 'Error loading message';
        });
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

header {
  background-color: #42b983;
  padding: 20px;
  color: white;
}

main {
  margin: 20px;
}

footer {
  background-color: #2c3e50;
  padding: 10px;
  color: white;
  /*position: fixed;*/
  width: 100%;
  bottom: 0;
}
</style>