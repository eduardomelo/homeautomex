using AutoMapper;
using HomeAutomex.Models;
using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeAutomex.AutoMapper.Mappers
{
    public class AmbienteAutoMapper : Profile
    {
        protected override void Configure()
        {
            Mapper.CreateMap<Ambiente, AmbienteModel>()
                    .ForMember(e => e.ChaveResidencia, opt => opt.MapFrom(e => e.Residencia.Chave));
            Mapper.CreateMap<AmbienteModel, Ambiente>();

            base.Configure();
        }

    }
}