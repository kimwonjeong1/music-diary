<template>
  <div id="signup">
    <v-container class="mx-auto" style="max-width:600px; margin-top:100px">
      <div class="inputForm mx-auto">
      <v-form @submit.prevent="signup(signupData)">
        <v-row align="center" no-gutters>
                <v-text-field v-model="signupData.email"
                      :rules="emailRules"
                      label="email"
                      required
                 ></v-text-field>
        </v-row>
        <v-text-field v-model="signupData.nickname"
                      :rules="[rules.nicknamerequired, rules.max]"
                      label="nickname"
                      required
        ></v-text-field>
        <v-text-field
          v-model="signupData.password"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.passwordrequired, rules.min]"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="Password"
          hint="At least 8 characters"
          counter
          @click:append="show1 = !show1"
        ></v-text-field>
        <v-text-field
          v-model="reenter"
          :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="reenterRules"
          :type="show2 ? 'text' : 'password'"
          name="input-10-1"
          label="Re-enter"
          hint="At least 8 characters"
          counter
          @click:append="show2 = !show2"
        ></v-text-field>
        <div align="right" class="my-4">
          <v-btn type="submit" depressed small color = "primary">회원가입</v-btn>
        </div>
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
      signupData: {
        nickname: '',
        email: '',
        password: 'Password',
      },
      reenter: '',
      certicode: '',
      show1: false,
      show2: false,
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      reenterRules: [
        v => v== this.signupData.password || 'Password is not equals',
        v => !!v || 'Re-enter is required',
      ],
      rules: {
        passwordrequired: value => !!value || 'Password is required.',
        nicknamerequired: value => !!value || 'Nickname is required',
        min: v => v.length >= 8 || 'Min 8 characters',
        max: v => v.length <= 10 || 'Max 10 characters',
      }
    };
  },
  methods: {
    ...mapActions(["signup"]),
    sendCode:function(){
      // this.$router.push({ name: 'home' })
    },
  },
};
</script>

<style>
</style>