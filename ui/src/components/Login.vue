<template>
    <div>
      <div>
        <h2>Log in to your account</h2>
        <form @submit.prevent="login">
          <div>
            <div>
              <label for="email-address">Email address</label>
              <input v-model="email" id="email-address" name="email" type="email" autocomplete="email" required placeholder="Email address">
            </div>
            <div>
              <label for="password">Password</label>
              <input v-model="password" id="password" name="password" type="password" autocomplete="current-password" required placeholder="Password">
            </div>
          </div>
          <div>
            <button type="submit">Log in</button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  export default {
    data() {
      return {
        email: '',
        password: ''
      }
    },
    methods: {
      login() {
        // Your login logic here
      }
    }
  }
  </script>
  