import Env from './env';

let appContext = Env.isDevEnv ? "" : "/viz";

let config = {
  env: Env,
  appContext:appContext,
  loginPage:appContext + "/#/login",
  homePage:appContext + "/#/frame/manage/user/index",

  fileServerContext:'/file-server',
  mockServerContext:"/mock-server",

  errorMessage:"数据请求出错，请稍后尝试"
};
export default config;
