import projectList from './json/projectList'
import projectDetails from './json/projectDetails'
import addressDetails from './json/addressDetails'
import searchResult from './json/searchResult'

export default {
  fetchProjectList: () => {
    return projectList;
  },

  fetchProjectDetails: () => {
    return projectDetails;
  },

  fetchAddressDetails: () => {
    return addressDetails;
  },

  fetchSearch: () => {
    return searchResult;
  }
}
