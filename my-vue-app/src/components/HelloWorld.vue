<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>
      This is a customized message from HelloWorld.vue.<br>
      You can add more content and components here.
    </p>
    <button @click="navigateToMessage">Click Me</button>
    <div class="additional-content">
      <h2>Additional Content</h2>
      <p>Here you can add more content and components.</p>

      <p>
        "Except for those who are guilty of fault, they are in blame for those who pursue the pleasure of the soul with pain."
      </p>
      <p>
        "Pain itself is to be reprehended in pleasure, it is to be accepted, and there is no consequence for avoiding it."      </p>
      <p>
        3- "In order to achieve the least, one must exert effort unless the result provides some benefit from it."      </p>
      <p>
        4- "Lorem Ipsum is simply dummy text of the printing and typesetting industry." 
                <a href="https://www.example.com" target="_blank">Visit Example.com</a>


</p>
    <!-- < <p>
        <a href="https://www.example.com" target="_blank">Visit Example.com</a>
      </p>>
     <p>
        <a href="http://localhost:8080/api/message" target="_blank">Go to API Message</a>
      </p> -->


    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: {
      type: String,
      default: 'Hello from Vue.js!'
    }
  },
  methods: {
    navigateToMessage() {
      window.location.href = 'http://localhost:8080/api/message';
    }
  }
}
</script>

<style scoped>
h1 {
  color: #42b983;
}
p {
  font-size: 18px;
  color: #333;
}
button {
  background-color: #42b983;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
}
button:hover {
  background-color: #2c3e50;
}
</style>