<template>
  <div id="login">
    <v-container class="mx-auto" style="max-width:450px; margin-top:150px">
      <div class="inputForm mx-auto">
      <v-form @submit.prevent="login(loginData)">
        <v-text-field v-model="loginData.email"
                      :rules="emailRules"
                      label="email"
                      required
        ></v-text-field>
        <v-text-field
          v-model="loginData.password"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.min]"
          :type="show1 ? 'text' : 'password'"
          label="Password"
          hint="At least 8 characters"
          counter
          @click:append="show1 = !show1"
        ></v-text-field>
      <v-row align="center">
        <v-col cols="12" sm="7">
        </v-col>
        <v-col cols="12" sm="2" class="my-5">
            <v-btn type="submit" depressed small color = "primary">로그인</v-btn>
        </v-col>
        <v-col cols="12" sm="3" class="my-5">
            <v-btn depressed small color = "primary" @click="signup">회원가입</v-btn>
        </v-col>
      </v-row>
      </v-form>
    </div>
    </v-container>
    
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      loginData: {
        email: '',
        password: '',
      },
      show1: false,
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      rules: {
        required: value => !!value || 'Password is Required.',
        min: v => v.length >= 8 || 'Min 8 characters',
      }
    };
  },
  methods: {
    ...mapActions(["login"]),
    signup: function(){
      this.$router.push({ name: 'signup' })
    },
  },
};
</script>

<style>
</style>